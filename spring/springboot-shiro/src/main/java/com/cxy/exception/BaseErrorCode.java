package com.cxy.exception;

public enum BaseErrorCode {
	
	// system exception
	BAD_REQUEST("400", "Bad Request!"),
	NOT_AUTHORIZATION("401", "NotAuthorization!"),
	NO_FOUND_EXCEPTION("404", "The requested resource is not available."),
	METHOD_NOT_SUPPORTED_EXCEPTION("405", "Method Not Allowed."),
	NOT_ACCEPTABLE_EXCEPTION("406", "Not Acceptable."),
	NULL_POINTER_EXCEPTION("407", "NullPointerException!"),
	NOT_SUPPORTED_EXCEPTION("415", "Content type not supported"),
	SERVER_EXCEPTION("500", "Internal Server Error"),
	RUNTIME_EXCEPTION("1000", "RuntimeException!"),
	OUT_OF_MEMORY_EXCEPTION("1001", "OutOfMemoryError!"),
	CLASS_CAST_EXCEPTION("1002", "ClassCastException!"),
	IO_EXCEPTION("1003", "IOException!"),
	NOT_SUCH_METHOD_EXCEPTION("1004", "NoSuchMethodException!"),
	INDEX_OUT_OF_BOUNDS_EXCEPTION("1005", "IndexOutOfBoundsException!"),
	MAX_UPLOAD_SIZE_EXCEPTION("1006", "file size is too large!"),
	MULTIPART_EXCEPTION("1007", "MultipartException!"),
	SQL_EXCEPTION("1008", "SQLException"),
	
	// custom exception
	DAO_EXCEPTION("10001", "data access exception."),
	UNKNOWN_EXCEPTION("10002", "unknown exception."),
	PARAMETERS_ERROR("10003", "parameters error."),

	AUTH_TOKEN_INVALID("10011", "AUTH TOKEN INVALID"),
	AUTH_LOGIN_IP_ERROR("10012", "LOGIN IP ERROR"),
	
	JEDIS_ERROR("10020", "LOGIN IP ERROR");

	private final String code;

	private final String description;

	private BaseErrorCode(String code, String description) {
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
