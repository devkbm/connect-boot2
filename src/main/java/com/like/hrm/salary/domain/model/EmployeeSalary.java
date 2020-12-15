package com.like.hrm.salary.domain.model;

import java.time.LocalDate;
import java.util.List;

public class EmployeeSalary {

	// 급여일 + 직원번호 + 급여구분으로 구성됨
	String id;
	
	// 직원번호
	String empId;
		
	// 급여일
	LocalDate payDay;
	
	// 급여구분
	String payType;
	
	// 급여계산 기준 인사정보
	
	// 급여항목
	List<EmployeeSalaryItem> items;	
	
	
}
