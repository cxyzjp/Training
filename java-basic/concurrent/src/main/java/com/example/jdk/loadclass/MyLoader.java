package com.example.jdk.loadclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class MyLoader extends ClassLoader{
	
	private static final String keyPath = "e:/key.txt";
	private String basePath = "D:/Workspaces/Eclipse/Training/build/classes/";
	private String className = "com.training.A";

	private Map<String, Class> loadedClassPool = new HashMap<String, Class>();
	
	public MyLoader(){
	}
	
	/**
	 * @param className 例如 com.test.A
	 */
	public MyLoader(String className){
		this.className = className;
	}
	
	/**
	 * @param basePath 例如 D:/Workspaces/
	 * @param className 例如 com.test.A
	 */
	public MyLoader(String basePath, String className){
		this.basePath = basePath;
		this.className = className;
	}
	
	/**
	 * 加载类
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Class loadClass(){
		Class clazz = null;
		if(loadedClassPool.containsKey(className)){
			clazz = loadedClassPool.get(className);
		}else{
//			try {  
//                if (clazz == null) {  
//                	clazz = super.loadClass(className, false);  
//                    if (clazz != null) {  
//                        System.out.println("系统加载成功：" + className);  
//                    }  
//                }  
//            } catch (ClassNotFoundException e) {  
//                System.out.println("系统无法加载：" + className);  
//            }  
              
            try {  
                if (clazz == null) {
                	byte[] classData = decrypt();
                	clazz = defineClass(className, classData, 0, classData.length);
                    if (clazz != null) {  
                    	loadedClassPool.put(className, clazz);
                        System.out.println("自定义加载成功：" + className);  
                    }  
                }  
            } catch (Exception e) {  
                System.out.println("自定义无法加载：" + className);  
            }  
		}
		
		return clazz;
	}
	
	/**
	 * 创建Key
	 */
	public void createKey(){
		try {
			SecureRandom sr = new SecureRandom();  
			KeyGenerator kg = KeyGenerator.getInstance("DES");  
			kg.init(sr);  
			SecretKey key = kg.generateKey();  
			System.out.println(key.toString());
			byte rawKeyData[] = key.getEncoded();  
			FileOutputStream fo = new FileOutputStream(new File(keyPath));  
			fo.write(rawKeyData);  
			fo.close();  
		} catch (Exception e) {
			e.printStackTrace();
		}
    	}
	
	/**
	 * 加密class文件
	 */
	public void crypt(){
		try {
			SecureRandom sr = new SecureRandom();  
			FileInputStream fi = new FileInputStream(new File(keyPath));  
			byte rawKeyData[] = new byte[fi.available()];  
			fi.read(rawKeyData);  
			fi.close();  
			DESKeySpec dks = new DESKeySpec(rawKeyData);  
			SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);  
			Cipher cipher = Cipher.getInstance("DES");  
			cipher.init(Cipher.ENCRYPT_MODE, key, sr);  
			
			String classPath = basePath + className.replace(".", "/") +".class";
			FileInputStream fi2 = new FileInputStream(new File(classPath));  
			byte data[] = new byte[fi2.available()];  
			fi2.read(data);  
			fi2.close();  
			byte encryptedData[] = cipher.doFinal(data);  
			FileOutputStream fo = new FileOutputStream(new File(classPath));  
			fo.write(encryptedData);  
			fo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解密class文件，返回源文件的数据
	 */
	public byte[] decrypt(){
		try {
			SecureRandom sr = new SecureRandom();  
			FileInputStream fi = new FileInputStream(new File(keyPath));  
			byte rawKeyData[] = new byte[fi.available()];  
			fi.read(rawKeyData);  
			fi.close();  
			DESKeySpec dks = new DESKeySpec(rawKeyData);  
			SecretKey key = SecretKeyFactory.getInstance("DES").generateSecret(dks);  
			Cipher cipher = Cipher.getInstance("DES");  
			cipher.init(Cipher.DECRYPT_MODE, key, sr);  
			
			String classPath = basePath + className.replace(".", "/") +".class";
			FileInputStream fi2 = new FileInputStream(new File(classPath));  
			byte encryptedData[] = new byte[fi2.available()];  
			fi2.read(encryptedData);  
			fi2.close();  
			byte decryptedData[] = cipher.doFinal(encryptedData);
			
			return decryptedData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		MyLoader myLoader = new MyLoader("com.training.A");
//		// 创建Key
//		myLoader.createKey();
//		// 加密
//		myLoader.crypt();
		try {
			Class c = myLoader.loadClass();
			Object obj = c.newInstance();
			
			// 用getDeclaredMethod()和setAccessible(true) 可以访问private方法
			Method mainMethod = c.getDeclaredMethod("hello", null); 
			mainMethod.setAccessible(true);
			
			// 第二个参数是方法的参数
			mainMethod.invoke(obj,null );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
