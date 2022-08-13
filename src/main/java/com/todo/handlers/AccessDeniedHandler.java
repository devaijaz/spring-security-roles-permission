package com.todo.handlers;

public class AccessDeniedHandler {
	private final Integer code = 403;
	private final String message = "Access Denied";
	private final String status= "error";
	public AccessDeniedHandler() {
		
	}
	public Integer getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public String getStatus() {
		return status;
	}
	
	
}
