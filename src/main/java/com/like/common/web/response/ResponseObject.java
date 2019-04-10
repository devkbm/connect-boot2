package com.like.common.web.response;

import lombok.Data;

@Data
public class ResponseObject<T> {
	
	T data;
	
	int total;
	
	boolean success;
	
	String message;
	
	public ResponseObject(T data, int total, boolean success, String message) {
		this.data = data;
		this.total = total;
		this.success = success;
		this.message = message;
	}
}
