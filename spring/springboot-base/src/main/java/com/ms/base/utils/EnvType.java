package com.ms.base.utils;

public enum EnvType {
	/**
	 * 错误类型：
	 * SYSTEM: 系统错误
	 * SERVICE: service层错误
	 * CONTROLLER: controller层错误
	 */ 
	
	DEBUG_VERSION("debug", "本地调试环境"),

	TEST_VERSION("test", "QA测试环境"),
	
	RELEASE_VERSION("release", "产品发布环境");

	// others

	private final String code;

	private final String description;

	private EnvType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String code() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}
}
