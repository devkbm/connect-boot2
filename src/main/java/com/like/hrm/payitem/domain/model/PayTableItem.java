package com.like.hrm.payitem.domain.model;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PayTableItem {

	Long id;
	
	String code1;
	
	String code2;
	
	String code3;
	
	BigDecimal ammount;
	
	String comment;
}
