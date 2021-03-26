package com.like.hrm.employee.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.Family;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

@Transactional
@Service
public class EmployeeFamilyService {

	private EmployeeRepository repository;
	
	public EmployeeFamilyService(EmployeeRepository repository) {
		this.repository = repository;
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
		
		repository.save(emp);
	}	
	
	private Employee getEmployeeInfo(String empId) {
		return repository.findById(empId)
						 .orElseThrow(() -> new EntityNotFoundException(empId + " 사번이 존재하지 않습니다."));
	}
}
