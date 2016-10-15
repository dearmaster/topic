package com.master.core.classloader;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

public class JarClassLoader extends ClassLoader {
	 public static Hashtable resources = new Hashtable();
	 
	    public static JarClassLoader loader = new JarClassLoader();

	    public static void load(byte[] resource) throws Exception {
	        //class资源及实体缓存
	        ArrayList classNames = new ArrayList();
	        ArrayList classBuffers = new ArrayList();
	        // 存储依赖类
	        HashMap depends = new HashMap();
	        // 将byte[]转为JarInputStream
	        JarInputStream jar = new JarInputStream(new ByteArrayInputStream(
	                resource));
	        Manifest manifest = jar.getManifest();
	        // 依次获得对应JAR文件中封装的各个被压缩文件的JarEntry
	        JarEntry entry;
	        while ((entry = jar.getNextJarEntry()) != null) {
	            // 当找到的entry为class时
	            if (entry.getName().toLowerCase().endsWith(".class")) {
	                // 将类路径转变为类全称
	                String name = entry.getName().substring(0, entry.getName().lastIndexOf(".class")).replace("/", ".");
	                
	                if(!name.contains("$")) {
	                	byte[] data = getResourceData(jar);
		                // 缓存类名及数据
		                classNames.add(name);
		                classBuffers.add(data);
	                }

	            } else {
	                // 非class结尾但开头字符为'/'时
	                if (entry.getName().charAt(0) == '/') {
	                    resources.put(entry.getName(), getResourceData(jar));
	                // 否则追加'/'后缓存    
	                } else {
	                    resources.put("/" + entry.getName(), getResourceData(jar));
	                }
	            }
	        }


	        while (classNames.size() > 0) {
	            //获得类路径全长
	            int n = classNames.size();
	            
	            //遍历所有还未加载的类
	            for (int i = classNames.size() - 1; i >= 0; i--) {
	                try {
	                    //查询指定类
	                    loader.defineClass((String) classNames.get(i),
	                            (byte[]) classBuffers.get(i), 0,
	                            ((byte[]) classBuffers.get(i)).length);

	                    
	                    //加载类后从list中去掉
	                    classNames.remove(i);
	                    classBuffers.remove(i);
	                } catch (NoClassDefFoundError e) {
	                    depends.put((String) classNames.get(i), e.getMessage()
	                            .replaceAll("/", "."));
	                } catch (UnsupportedClassVersionError e) {
	                    //jre版本错误提示
	                    throw new UnsupportedClassVersionError(classNames.get(i)
	                            + ", " + System.getProperty("java.vm.name") + " "
	                            + System.getProperty("java.vm.version") + ")");
	                }
	            }
	            
	            //如果遍历完一次发现没有一个类被加载，则证明剩下的类都不能被加载
	            if (n == classNames.size()) {
	                for (int i = 0; i < classNames.size(); i++) {
	                    System.err.println("NoClassDefFoundError:"
	                            + classNames.get(i));
	                    String className = (String) classNames.get(i);
	                    while (depends.containsKey(className)) {
	                        className = (String) depends.get(className);
	                    }
	                }
	                break;
	            }
	        }
	        Thread.currentThread().setContextClassLoader(loader);
	    }

	    /** *//**
	     * 获得指定路径文件的byte[]形式
	     * @param name
	     * @return
	     */
	    final static public byte[] getDataSource(String name) {
	        FileInputStream fileInput;
	        try {
	            fileInput = new FileInputStream(new File(name));
	        } catch (FileNotFoundException e) {
	            fileInput = null;
	        }
	        BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
	        return getDataSource(bufferedInput);
	    }
	    
	    /** *//**
	     * 获得指定InputStream的byte[]形式
	     * @param name
	     * @return
	     */
	    final static public byte[] getDataSource(InputStream is) {
	        if (is == null) {
	            return null;
	        }
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        byte[] arrayByte = null;
	        try {
	            byte[] bytes = new byte[8192];
	            bytes = new byte[is.available()];
	            int read;
	            while ((read = is.read(bytes)) >= 0) {
	                byteArrayOutputStream.write(bytes, 0, read);
	            }
	            arrayByte = byteArrayOutputStream.toByteArray();
	        } catch (IOException e) {
	            return null;
	        } finally {
	            try {
	                if (byteArrayOutputStream != null) {
	                    byteArrayOutputStream.close();
	                    byteArrayOutputStream = null;
	                }
	                if (is != null) {
	                    is.close();
	                    is = null;
	                }

	            } catch (IOException e) {
	            }
	        }
	        return arrayByte;
	    }

	    /** *//**
	     * 获得指定JarInputStream的byte[]形式
	     * @param jar
	     * @return
	     * @throws IOException
	     */
	     final static private byte[] getResourceData(JarInputStream jar)
	            throws IOException {
	        ByteArrayOutputStream data = new ByteArrayOutputStream();
	        byte[] buffer = new byte[8192];
	        int size;
	        while (jar.available() > 0) {
	            size = jar.read(buffer);
	            if (size > 0) {
	                data.write(buffer, 0, size);
	            }
	        }
	        return data.toByteArray();
	    }

	     /** *//**
	      * 重载的getResource,检查是否重复包含
	      */
	    public URL getResource(String name) {
	        if (resources.containsKey("/" + name)) {
	            try {
	                return new URL("file:///" + name);
	            } catch (MalformedURLException e) {
	                e.printStackTrace();
	            }
	        }
	        return super.getResource(name);
	    }

	    /** *//**
	     * 执行指定class类
	     * @param clz
	     * @param methodName
	     * @param args
	     */
	    public static void callVoidMethod(Class clz, String methodName,
	            String[] args) {
	        Class[] arg = new Class[1];
	        arg[0] = args.getClass();
	        try {
	            Method method = clz.getMethod(methodName, arg);
	            Object[] inArg = new Object[1];
	            inArg[0] = args;
	            method.invoke(clz, inArg);
	        } catch (Exception e) {
	            System.err.println(e.getMessage());
	        }
	    }
	    
	     /** *//**
	      * 重载的getResourceAsStream,检查是否重复包含
	      */
	    public InputStream getResourceAsStream(String name) {
	        if (name.charAt(0) == '/') {
	            name = name.substring(1);
	        }
	        if (resources.containsKey("/" + name)) {
	            return new ByteArrayInputStream((byte[]) resources.get("/" + name));
	        }
	        return super.getResourceAsStream(name);
	    }
	    

//	private static void test() {
//		String name = "com.mars.java.core.classloader.CustomizeClassLoader.class";
//		String str = name.substring(0, name.lastIndexOf(".class"));
//		System.out.println(str);
//	}

}
