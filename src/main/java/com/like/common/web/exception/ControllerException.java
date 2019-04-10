package com.like.common.web.exception;

public class ControllerException extends RuntimeException {
		
	private static final long serialVersionUID = -4588969707725570905L;
	
	private String message;
	
	public ControllerException(String msg) {
		this.message = msg;
	}
	
	public String getMessage() {
		return this.message;
	}

}
