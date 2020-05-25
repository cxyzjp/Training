package com.ms.base.bean.login;

public enum LoginErrorCode {

	PARAM_USER_NAME_IS_EMPTY("10101", "PARAM USER NAME IS EMPTY"),
	PARAM_PASSWORD_IS_EMPTY("10102", "PARAM PASSWORD IS EMPTY"),
	LOGIN_FAILED("10103", "LOGIN FAILED"),
	USER_IS_NOT_EXIST_OR_PASSWORD_IS_ERROR("10104", "USER IS NOT EXIST OR PASSWORD IS ERROR"),
	PLATFORM_IS_NOT_SUPPORTED("10105", "THE PLATFORM IS NOT SUPPORTED NOW"),
	PLATFORM_REQUEST_FAILED("10106", "THE THIRD PLATFORM REQUEST FAILED"),
	VERIFY_CODE_ERROR("10107", "Verify code error"),
	SMS_VERIFY_CODE_ERROR("10108", "SMS verify code error");

	// others

	private final String code;

	private final String description;

	private LoginErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return this.code;
	}

	public String getDescription() {
		return this.description;
	}
}
