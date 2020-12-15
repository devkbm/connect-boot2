package com.like.hrm.salary.domain.model;

import java.math.BigDecimal;

public class EmployeeSalaryItem {

	
	EmployeeSalary employeeSalary;
	
	// 식별자
	Long id;
		
	// 급여항목
	String payItem;
	
	// 금액
	BigDecimal amount;
	
	// 계산일수
	int calcDays;
	
	// 계산식
	String calcFomula;
		
}
