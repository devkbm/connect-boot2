package com.like.common.web.exception;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHanlder /*extends ResponseEntityExceptionHandler*/ {
	
	/*@ExceptionHandler(value = ControllerException.class)  
    protected ResponseEntity<Object> handleException(RuntimeException e, WebRequest request) throws IOException{		
				
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);  		
	}*/
	
}
