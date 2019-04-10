package com.like.board.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PasswordType {
	SHA224("SHA224","SHA2-224BIT"),
	SHA256("SHA256","SHA2-256BIT"),
	SHA384("SHA384","SHA2-384BIT"),
	SHA512("SHA512","SHA2-512BIT");
	
	private String code;
	private String name;
	
	private PasswordType(final String code, final String name) {
		this.code = code;
        this.name = name;
	}
	
    public String getCode() {
        return code;
    }
    	
    public String getName() {
        return name;
    }
}
