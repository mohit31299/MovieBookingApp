package com.cognizant.UserManagement.Model;

import org.springframework.http.HttpStatus;

public class ApiResponse {
	

	private String msg;

	private HttpStatus httpStatus;

	public ApiResponse() {
	}

	public ApiResponse(String msg, HttpStatus status) {
		super();
		this.msg = msg;
		this.httpStatus = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public HttpStatus getHttpMethod() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}


}
