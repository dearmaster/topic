package com.master.core.classloader;

import java.io.File;

public class CustomizeClassLoaderTest {
	
	public static void main(String[] args) {
		sameClassDiffClassLoader();
	}
	
	//Try to load a class that is not in current classpath
	public static void loadClassOutOfClasspath() {
		CustomizeClassLoader ccl = new CustomizeClassLoader(null, "E:\\develop\\workspaces\\trunk\\spring-resteasy-demo\\target\\classes");//\\com\\mars\\resteasy\\demo\\pojo
		Class<?> User = null;
		Object user = null;
		try {
			User = ccl.loadClass("com.mars.resteasy.demo.pojo.User");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if(User != null) {
			try {
				user = User.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		System.out.println(user);
	}
	
	//If a same class was loaded by different ClassLoader, they are treated to be different Class
	public static void sameClassDiffClassLoader() {
		CustomizeClassLoader ccl1 = new CustomizeClassLoader(null, "E:\\develop\\workspaces\\trunk\\spring-resteasy-demo\\target\\classes");
		CustomizeClassLoader ccl2 = new CustomizeClassLoader(null, "E:\\develop\\workspaces\\trunk\\spring-resteasy-demo\\target\\classes");
		Class<?> User1 = null;
		Class<?> User2 = null;
		Class<?> User3 = null;
		try {
			User1 = ccl1.loadClass("com.mars.resteasy.demo.pojo.User");
			User2 = ccl1.loadClass("com.mars.resteasy.demo.pojo.User");
			User3 = ccl2.loadClass("com.mars.resteasy.demo.pojo.User");
			System.out.println(User1 == User2); //true
			System.out.println(User1 == User3); //false
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void test() {
		String path = System.getProperty("java.class.path");
		System.setProperty("java.class.path", System.getProperty("java.class.path") + File.pathSeparatorChar);
		path = System.getProperty("java.class.path");
		System.out.println(path);
		System.out.println(CustomizeClassLoaderTest.class.getClassLoader());
		System.out.println(CustomizeClassLoaderTest.class.getClassLoader().getParent());
		System.out.println(CustomizeClassLoaderTest.class.getClassLoader().getParent().getParent());
	}

}
