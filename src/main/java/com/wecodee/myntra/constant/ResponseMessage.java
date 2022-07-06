package com.wecodee.myntra.constant;

public enum ResponseMessage {

	// Login Messages
	LOGIN_SUCCESS("Login Successfull"), 
	LOGIN_FAILED("Login Failed"),

	// Register Messages
	REGISTER_SUCCESS("Registered Successfull"), 
	REGISTER_FAILED("Register Failed"),;

	private String message;

	ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
