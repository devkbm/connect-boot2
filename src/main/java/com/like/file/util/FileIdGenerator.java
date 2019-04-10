package com.like.file.util;

import java.nio.ByteBuffer;
import java.util.Base64;
import java.util.UUID;

public class FileIdGenerator {
	
	public static String generateUUID() {
		UUID uuid = UUID.randomUUID();
		
		// URL에 포함될 수 있는 Base64 문자열로 변환
	    ByteBuffer uuidBytes = ByteBuffer.wrap(new byte[16]);
	    uuidBytes.putLong(uuid.getMostSignificantBits());
	    uuidBytes.putLong(uuid.getLeastSignificantBits());
	    	    	    
	    return Base64.getUrlEncoder().encodeToString(uuidBytes.array());
	}
	
	public static String generateFileId() {
		return FileIdGenerator.generateUUID() + System.nanoTime(); 
	}
	
}
