package com.ms.common.authentication;

public enum AuthenticationErrorCode {
	AUTHENTICATION_CONSOLE_NETWORK_ERROR("10201", "AUTHENTICATION CONSOLE NETWORK ERROR"),
	AUTHENTICATION_CONSOLE_RESULT_FORMAT_ERROR("10202", "AUTHENTICATION CONSOLE RESULT FORMAT ERROR"),
	VERSION_IS_EMPTY_ERROR("10203", "version can't be empty."),
	SIGNATURE_NONCE_IS_EMPTY_ERROR("10204", "signature nonce can't be empty."),
	ACCESS_KEY_IS_EMPTY_ERROR("10205", "access key id can't be empty."),
	SIGNATURE_IS_EMPTY_ERROR("10206", "signature can't be empty."),
	SIGNATURE_METHOD_IS_EMPTY_ERROR("10207", "signature method can't be empty."),
	TIMESTAMP_IS_EMPTY_ERROR("10208", "timestamp can't be empty."),
	SIGNATURE_VERSION_IS_EMPTY_ERROR("10209", "signature version can't be empty."),
	AUTHENTICATION_MISSING_ACCESS_KEY_ID_ERROR("10210", "missing access key id"),
	AUTHENTICATION_UNAUTHORIZATION_ERROR("10211", "unauthorization");

	// others

	private final String code;

	private final String description;

	private AuthenticationErrorCode(String code, String description) {
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
