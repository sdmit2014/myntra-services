package com.wecodee.myntra.constant;

public enum ResponseMessage {
	
	OPERATION_SUCCESS("Operation Success"),
	OPERATION_FAILED("Operation Failed"),
	USER_NOT_FOUND("User not found"),

	// Login Messages
	LOGIN_SUCCESS("Login Successfull"), 
	INVALID_PASSWORD("Invalid Password"),
	LOGIN_FAILED("Login Failed"),

	// Register Messages
	REGISTER_SUCCESS("Registered Successfull"), 
	REGISTER_FAILED("Register Failed");

	private String message;

	ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
