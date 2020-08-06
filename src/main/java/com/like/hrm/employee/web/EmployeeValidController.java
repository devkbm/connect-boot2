package com.like.hrm.employee.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

@RestController
public class EmployeeValidController {

	private EmployeeRepository employeeRepository;
	
	public EmployeeValidController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;		
	}
	
	@GetMapping("/hrm/employee/{id}/valid")
	public ResponseEntity<?> getEmployee(@PathVariable String id) {
		
		boolean exist = employeeRepository.isEmployee(id);
					
		return WebControllerUtil.getResponse(exist											
											,exist == true ? "직원정보가 존재합니다." : "직원정보가 존재하지 않습니다."
											,HttpStatus.OK);
	}
	
	
}
