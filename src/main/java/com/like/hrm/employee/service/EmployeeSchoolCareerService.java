package com.like.hrm.employee.service;

import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.SchoolCareer;
import com.like.hrm.employee.domain.repository.EmployeeRepository;


@Transactional
public class EmployeeSchoolCareerService {

	private EmployeeRepository employeeRepository;
				
	public EmployeeSchoolCareerService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;	
	}
	
	public SchoolCareer getSchoolCareer(String empId, Long id) {
		Employee emp = getEmployeeInfo(empId);
		
		return emp.getSchoolCareerList().get(id);
	}
	
	public void saveSchoolCareer(EmployeeDTO.SaveEducation dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());
		
		SchoolCareer education = emp.getSchoolCareerList().get(dto.getEducationId());
		
		if (education == null) {
			education = dto.newEntity(emp);
		} else {
			dto.modifyEnity(education);
		}
		
		emp.getSchoolCareerList().add(education);
		
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
