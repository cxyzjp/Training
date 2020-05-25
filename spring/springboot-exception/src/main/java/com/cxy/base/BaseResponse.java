package com.cxy.base;

import java.io.Serializable;

public class BaseResponse<T> implements Serializable{
	private static final long serialVersionUID = 7106996467392858727L;
	private String code = "200";
	private String message = "OK";
	private String description;
	private T data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
