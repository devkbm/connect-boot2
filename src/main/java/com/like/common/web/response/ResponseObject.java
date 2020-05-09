package com.like.common.web.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ResponseObject<T> {
	
	T data;
	
	int total;
	
	boolean success;
	
	String message;
	
	Timestamp timestamp;
	
	String status;
	
	String error;
	
	String path;
	
	public ResponseObject(T data, int total, boolean success, String message) {
		this.data = data;
		this.total = total;
		this.success = success;
		this.message = message;
	}
}
