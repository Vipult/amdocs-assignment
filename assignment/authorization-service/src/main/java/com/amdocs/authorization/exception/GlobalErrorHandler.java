package com.amdocs.authorization.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalErrorHandler {
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ResponseError handleCustomException(ConstraintViolationException ex) {
		ResponseError responseError = new ResponseError();
		List<String> errorMessages = new ArrayList();
		for (ConstraintViolation constraintViolation : ex.getConstraintViolations()) {
		errorMessages.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
		}
		responseError.setErrorMessage(errorMessages);
		responseError.setStatusCode(HttpStatus.BAD_REQUEST.value());
		return responseError;
 
	}
	
	 @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
	         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	    }
		
		@ExceptionHandler(BadRequestException.class)
	    public ResponseEntity<?> badRequestException(BadRequestException ex, WebRequest request) {
	         ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
	         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	    }
	}