package com.like.hrm.salary.domain.repository;

import com.like.hrm.salary.domain.model.EmployeeSalary;

public interface EmployeeSalaryRepository {

	EmployeeSalary getEmployeeSalary();

	void saveEmployeeSalary();
	
	void deleteEmployeeSalary();
}
