package com.master.core.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

public class CustomizeClassLoader extends ClassLoader {


	private String baseDir;

	public CustomizeClassLoader(ClassLoader parent, String baseDir) {
		super(parent);
		this.baseDir = baseDir;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		byte[] bytes = loadClassBytes(name);
		Class<?> clazz = defineClass(name, bytes, 0, bytes.length);
		if (null == clazz) {
			throw new ClassFormatError();
		}
		return clazz;
		// return super.findClass(name);
	}

	private byte[] loadClassBytes(String name) throws ClassNotFoundException {

		try {
			String classFile = getClassFile(name);
			FileInputStream fis = new FileInputStream(classFile);
			
			FileChannel fc = fis.getChannel();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			WritableByteChannel outC = Channels.newChannel(baos);
			ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
			while (true) {
				int i = fc.read(buffer);
				if (i == 0 || i == -1) {
					break;
				}
				buffer.flip();
				outC.write(buffer);
				buffer.clear();
			}
			fis.close();
			return baos.toByteArray();
		} catch (IOException e) {
			throw new ClassNotFoundException(name);
		}

	}

	private String getClassFile(String name) {
		StringBuffer sb = new StringBuffer(baseDir);
		name = name.replace('.', File.separatorChar).concat(".class");
		sb.append(File.separator + name);
		return sb.toString();
	}

	@Override
	protected URL findResource(String name) {
		URL url =  super.findResource(name);
		if(null != url)
			return url;
		try {
			url = new URL("file:///" + converName(name));
			return url;
		} catch (MalformedURLException e) {
			return null;
		}
	}

	private String converName(String name) {
        StringBuffer sb = new StringBuffer(baseDir);
        name = name.replace('.', File.separatorChar);
        sb.append(File.separator + name);
        return sb.toString();
	}

}
