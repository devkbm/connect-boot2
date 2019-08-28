package com.like.hrm.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.repository.EmployeeRepository;
import com.like.hrm.employee.domain.service.EmployeeIdGenerator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("employeeService")
@Transactional
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	private EmployeeIdGenerator idGenerator;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeIdGenerator idGenerator) {
		this.employeeRepository = employeeRepository;
		this.idGenerator = idGenerator;
	}
	
	public Employee getEmployee(String id) {
		return employeeRepository.getEmployee(id);										
	}
	
	public void saveEmployee(Employee employee) {
		employeeRepository.saveEmployee(employee);
	}
	
	public void newEmployee(EmployeeDTO.NewEmployee dto) {								
		Employee emp = new Employee(idGenerator.generateEmpId(), dto.getName(), dto.getResidentRegistrationNumber());
		
		employeeRepository.saveEmployee(emp);
	}
	
	public void deleteEmployee(String id) {		
		employeeRepository.deleteEmployee(id);
	}
	
	public void saveDeptChangeHistory(EmployeeDTO.NewDept dto) {
		Employee emp = employeeRepository.getEmployee(dto.getId());
		
		DeptChangeHistory deptChangeHistory = new DeptChangeHistory(emp, dto.getDeptType(), dto.getDeptCode(), dto.getFromDate(), dto.getToDate());
				
		emp.addDeptChange(deptChangeHistory);
		
		employeeRepository.saveEmployee(emp);
	}
}
