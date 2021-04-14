package com.like.core.web.response;

import java.util.List;

import lombok.Data;

@Data
public class ResponseObjectList {

	List<?> data;
	
	int total;
	
	boolean success;
	
	String message;
	
	public ResponseObjectList(List<?> data, int total, boolean success, String message) {
		this.data = data;
		this.total = total;
		this.success = success;
		this.message = message;
	}
}
