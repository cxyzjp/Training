package com.cxy.exception;

public class ServiceException extends CustomException {
	private static final long serialVersionUID = -2989455881722632089L;
	
	public ServiceException(String code, String message) {
		super(code, message);
	}
	
	public ServiceException(String code, String message, String desc) {
		super(code, message, desc);
	}

}
