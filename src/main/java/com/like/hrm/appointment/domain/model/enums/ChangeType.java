package com.like.hrm.appointment.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ChangeType {

	// 부서정보	
	DEPT("DEPT",		"부서"), 
		
	// 인사정보	
	JOB("JOB", 			"인사"),
	
	// 상태정보
	STATUS("STATUS",	"근무상태");
	
	private String code;
	private String name;
	
	private ChangeType(final String code, final String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}	
	
	public static class Values {
        public static final String DEPT = "DEPT";
        public static final String JOB = "JOB";
        public static final String STATUS = "STATUS";
    }
}
