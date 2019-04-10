package com.like.file.dto;

import java.util.Map;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FileResponseDTO {

	String uid;
	
	String name;
	
	String status;
	
	String response;
	
	String url;	
	
	//String thumbUrl;
	
	//Map<String, String> linkProps;
	//String linkProps;
}
