package com.master.core.classloader;

public class JarClassLoaderTest {
	
    public static void main(String[] args) {
        byte[] resource = JarClassLoader.getDataSource("E:\\develop\\spring-resteasy-demo-classes.jar");
        try {
            JarClassLoader.load(resource);
            
            Object obj = Class.forName("com.mars.resteasy.demo.pojo.User", false, Thread.currentThread().getContextClassLoader()).newInstance();
            
            System.out.println(obj);
            
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
