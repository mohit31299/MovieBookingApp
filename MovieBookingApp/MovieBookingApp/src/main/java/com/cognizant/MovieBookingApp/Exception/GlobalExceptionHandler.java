package com.cognizant.MovieBookingApp.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.MovieBookingApp.Model.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MovieFoundException.class)
	public ResponseEntity<ApiResponse> movieFoundException(MovieFoundException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), HttpStatus.FOUND), HttpStatus.OK);
	}

	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ApiResponse> movieNotFoundException(MovieNotFoundException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.OK);
	}

	@ExceptionHandler(TicketNotFoundException.class)
	public ResponseEntity<ApiResponse> ticketNotFoundException(TicketNotFoundException e) {
		return new ResponseEntity<ApiResponse>(new ApiResponse(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.OK);
	}

	
}
