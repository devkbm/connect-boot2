package com.like.hrm.employee.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

@Service("employeeService")
@Transactional
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public Employee getEmployee(String id) {
		return employeeRepository.getEmployee(id);										
	}
	
	public void saveEmployee(Employee employee) {
		employeeRepository.saveEmployee(employee);
	}
	
	public void deleteEmployee(String id) {		
		employeeRepository.deleteEmployee(id);
	}
	
	public void saveDeptChangeHistory(String id, DeptChangeHistory deptChangeHistory) {
		Employee emp = employeeRepository.getEmployee(id);
		emp.addDeptChange(deptChangeHistory);
		
		employeeRepository.saveEmployee(emp);
	}
}
