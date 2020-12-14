package com.like.hrm.salary.domain.model;

import java.time.LocalDate;
import java.util.List;

public class EmployeeSalary {

	String empId;
		
	// 급여일
	LocalDate payDay;
	
	// 급여계산 기준 인사정보
	
	List<EmployeeSalaryItem> items;
	
	
}
