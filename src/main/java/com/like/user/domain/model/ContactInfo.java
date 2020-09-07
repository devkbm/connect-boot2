package com.like.user.domain.model;

import com.like.hrm.employee.domain.model.Employee;

public class ContactInfo {

	private Long id;
	
	private Employee employee;
	
	private String type;
	
	private String number;
	
	private boolean isLast;
	
	public ContactInfo(Employee employee
					  ,String type
					  ,String number
					  ,Boolean isLast) {
		this.employee = employee;
		this.type = type;
		this.number = number;
		this.isLast = isLast;
	}
}
