package com.like.hrm.appointment.domain.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ChangeType {

	// 부서정보	
	DEPT("H0002",	"부서"), 
		
	// 인사정보	
	JOB("H0003", 	"인사");
				
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
}
