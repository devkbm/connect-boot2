package com.like.board.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum BoardType {
	/**
	 * 관리자만 등록가능 
	 */
	NOTICE("NOTICE","공지사항"),
	
	BOARD("BOARD","일반게시판");
	
	private String code;
	private String name;
	
	private BoardType(final String code, final String name) {
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
