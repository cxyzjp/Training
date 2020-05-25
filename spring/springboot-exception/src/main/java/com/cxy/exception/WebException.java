package com.cxy.exception;

public class WebException extends CustomException {
	private static final long serialVersionUID = 4957812844662698969L;
	
	private String redirectUrl;

	public WebException(String code, String message, String redirectUrl) {
		super(code, message);
		this.redirectUrl = redirectUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
