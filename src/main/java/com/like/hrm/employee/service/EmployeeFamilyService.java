package com.like.hrm.employee.service;

import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.Family;
import com.like.hrm.employee.domain.model.License;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

@Transactional
public class EmployeeFamilyService {

	private EmployeeRepository employeeRepository;
	
	public EmployeeFamilyService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public Family getFamily(String empId, Long id) {
		Employee emp = getEmployeeInfo(empId);
						
		return emp.getFamilyList().get(id);
	}
	
	public void saveFamily(EmployeeDTO.SaveFamily dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());
		
		Family entity = emp.getFamilyList().get(dto.getId());
		
		if (entity == null) {
			entity = dto.newEntity(emp);
		} else {
			dto.modifyEntity(entity);
		}
		
		emp.getFamilyList().add(entity);
		
		employeeRepository.saveEmployee(emp);
	}	
	
	private Employee getEmployeeInfo(String empId) {
		Employee emp = employeeRepository.getEmployee(empId);
		
		if (emp == null) {
			throw new IllegalArgumentException(empId + " 사번이 존재하지 않습니다.");
		}
		
		return emp;
	}
}
