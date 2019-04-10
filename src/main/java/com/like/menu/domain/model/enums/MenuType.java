package com.like.menu.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MenuType {

	/**
	 * 프로그램 연결이 가능한 메뉴 
	 */
	ITEM("메뉴아이템"),
	
	/**
	 * 폴더(프로그램 선택 불가능)
	 */
	SUBMENU("서브메뉴"),
	
	/**
	 * 동일 레벨에서 메뉴를 묶음
	 */
	GROUP("그룹");
		
	private String name;
		
	private MenuType(final String name) {		
        this.name = name;               
	}	
	
    public String getName() {
        return name;
    }
	    
}
