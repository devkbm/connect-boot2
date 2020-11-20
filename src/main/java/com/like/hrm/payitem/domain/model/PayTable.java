package com.like.hrm.payitem.domain.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 급여테이블
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayTable {

	Long id;	
	
	String name;
	
	Boolean enabled;
	
	String typeCode1;
	
	String typeCode2;
	
	String typeCode3;
	
	String comment;
	
	List<PayTableItem> items;
	
	public PayTable(String name
				   ,Boolean enabled
				   ,String typeCode1
				   ,String typeCode2
				   ,String typeCode3
				   ,String comment)	{
		this.name = name;
		this.enabled = enabled;
		this.typeCode1 = typeCode1;
		this.typeCode2 = typeCode2;
		this.typeCode3 = typeCode3;
		this.comment = comment;
	}
	
	public void modify(String name
					  ,Boolean enabled
					  ,String typeCode1
					  ,String typeCode2
					  ,String typeCode3
					  ,String comment) {		
		this.name = name;
		this.enabled = enabled;
		this.typeCode1 = typeCode1;
		this.typeCode2 = typeCode2;
		this.typeCode3 = typeCode3;
		this.comment = comment;
	}
}
