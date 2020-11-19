package com.like.hrm.payitem.domain.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// 급여테이블
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
	
}
