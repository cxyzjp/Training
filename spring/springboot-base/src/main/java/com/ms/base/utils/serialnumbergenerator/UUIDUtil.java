package com.ms.base.utils.serialnumbergenerator;

import java.util.UUID;

public class UUIDUtil {

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}
	
	public static String generatorToken(){
		return getUUID().toUpperCase();
	}
	
}
