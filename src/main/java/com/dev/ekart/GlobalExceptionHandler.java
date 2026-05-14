package com.dev.ekart;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Aswin Senthilkumar
 *
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

	
	@ExceptionHandler(RuntimeException.class)
	public  ResponseEntity<ErrorResponse> runtimeexception(RuntimeException ex){
		ErrorResponse erResponse = new ErrorResponse(ex.getMessage());
		return ResponseEntity.ok(erResponse);
	}
	
	
}
