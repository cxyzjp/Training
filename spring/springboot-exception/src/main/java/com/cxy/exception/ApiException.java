package com.cxy.exception;

public class ApiException extends CustomException {
	private static final long serialVersionUID = -2989455881722632089L;
	
	public ApiException(String code, String message) {
		super(code, message);
	}
	
	public ApiException(String code, String message, String desc) {
		super(code, message, desc);
	}
	
}
