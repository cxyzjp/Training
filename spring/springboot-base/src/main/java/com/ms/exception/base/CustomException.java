package com.ms.exception.base;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -293212750835806820L;
	
	private String code;
	private String customMessage;
	private String description;
	
	public CustomException(){
		
	}

	public CustomException(String code, String message) {
		this.code = code;
		this.customMessage = message;
	}
	
	public CustomException(String code, String message, String desc) {
		this.code = code;
		this.customMessage = message;
		this.description = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomMessage() {
		return customMessage;
	}

	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
