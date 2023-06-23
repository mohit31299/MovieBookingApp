package com.cognizant.UserManagement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.UserManagement.Model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<ApiResponse> userFoundException(UserFoundException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), HttpStatus.FOUND), HttpStatus.OK);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiResponse> usernameNotFoundException(UsernameNotFoundException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.OK);
	}

	@ExceptionHandler(NotAuthenticatetException.class)
	public ResponseEntity<ApiResponse> notAuthenticatetException(NotAuthenticatetException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), HttpStatus.UNAUTHORIZED), HttpStatus.OK);
	}
	
	@ExceptionHandler(WrongAnswerException.class)
	public ResponseEntity<ApiResponse> wrongAnswerException(WrongAnswerException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.OK);
	}
}
