package com.like.hrm.employee.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.repository.EmployeeQueryRepository;

@Service
@Transactional
public class EmployeeQueryService {

	private EmployeeQueryRepository repository;
	
	public EmployeeQueryService(EmployeeQueryRepository repository) {
		this.repository = repository;		
	}
	
	public List<Employee> getEmployeeList(EmployeeDTO.SearchEmployee dto) {
		return repository.getEmployeeList(dto);
	}
}
