package com.ms.exception.base;

/**
 * 错误类型：
 * SYSTEM: 系统错误
 * SERVICE: service层错误
 * CONTROLLER: controller层错误
 */ 
public enum ExceptionType {
	
	SYSTEM_EXCEPTION("SYSTEM", "系统错误"),

	SERVICE_EXCEPTION("SERVICE", "service层错误"),
	
	CONTROLLER_EXCEPTION("CONTROLLER", "controller层错误");

	// others

	private final String code;

	private final String description;

	private ExceptionType(String code, String description) {
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
