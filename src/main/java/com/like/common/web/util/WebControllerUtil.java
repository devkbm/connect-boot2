package com.like.common.web.util;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.like.common.web.response.ResponseObject;
import com.like.common.web.response.ResponseObjectList;

import lombok.Builder;

public abstract class WebControllerUtil {
		
	private static final ObjectMapper mapper = new ObjectMapper();	
	
	public WebControllerUtil() {				
	}
	
	/**
	 * Json 스트링을 List 형태로 변환한다.
	 * @param jsonStr
	 * @param target
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> List<T> toBeanList(String jsonStr, Class<?> target) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
		if (jsonStr == null)
			return null;					
		return mapper.readValue(jsonStr, mapper.getTypeFactory().constructCollectionType(List.class, Class.forName(target.getName())));	
	}
			
	/**
	 * ResponseEntity 객체를 반환한다.
	 * @param data			결과 payload 데이터
	 * @param size			data의 사이즈
	 * @param success		성공 여부
	 * @param message		결과 메세지
	 * @param httpStatus	Http 응답 코드
	 * @return Rest 요청 결과 
	 */
	@Builder(builderMethodName="listBuilber")
	public static ResponseEntity<ResponseObjectList> getResponse(List<?> data, int size, boolean success, String message, HttpStatus httpStatus) {
									
		ResponseObjectList obj = new ResponseObjectList(data, size, success, message);			      
	    
	    return new ResponseEntity<ResponseObjectList>(obj, getResponseHeaders(), httpStatus);	    	    	    	    	
	}
	
	/**
	 * ResponseEntity 객체를 반환한다.
	 * @param data			결과 payload 데이터
	 * @param size			data의 사이즈
	 * @param success		성공 여부
	 * @param message		결과 메세지
	 * @param httpStatus	Http 응답 코드
	 * @return Rest 요청 결과 
	 */	
	public static <T> ResponseEntity<ResponseObject<T>> getResponse(T data, int size, boolean success, String message, HttpStatus httpStatus) {
		
		ResponseObject<T> obj = new ResponseObject<T>(data, size, success, message);		
					    	    
	    return new ResponseEntity<ResponseObject<T>>(obj, getResponseHeaders(), httpStatus);	    	    	    	    	
	}
	
	private static HttpHeaders getResponseHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		return responseHeaders;		
	}		 
	
}
