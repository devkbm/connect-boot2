package com.like.hrm.code.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum HrmTypeEnum {

	// 인사정보	
	JOB("JOB", 			"인사", "HRMAP001"),
		
	// 부서정보	
	DEPT("DEPT",		"부서", "HRMAP002"); 		
	
	private String code;
	private String name;
	private String parentCommonCodeId;
	
	private HrmTypeEnum(final String code, final String name, final String parentCommonCodeId) {
		this.code = code;
		this.name = name;
		this.parentCommonCodeId = parentCommonCodeId;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}	
	
	public String getParentCommonCodeId() {
		return this.parentCommonCodeId;
	}
	
	public static class Values {        
        public static final String JOB = "JOB";
        public static final String DEPT = "DEPT";
    }
}
