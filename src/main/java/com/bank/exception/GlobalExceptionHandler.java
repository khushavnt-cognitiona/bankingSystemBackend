package com.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<String> handleUserExists(UserAlreadyExistsException ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> handelInvalidCredentialsException(
			InvalidCredentialsException invalidCredentialsException) {
		return new ResponseEntity<String>(invalidCredentialsException.getMessage(), HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(KycNotVerifiedException .class)
	public ResponseEntity<String>handleKycNotVerifiedException(KycNotVerifiedException  kycNotVerifiedException){
		return new ResponseEntity<String>(kycNotVerifiedException.getMessage(),HttpStatus.NOT_FOUND);
		
	}
}
