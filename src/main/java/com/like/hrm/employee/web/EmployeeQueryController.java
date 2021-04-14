package com.like.hrm.employee.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.service.EmployeeQueryService;

@RestController
public class EmployeeQueryController {
	
	private EmployeeQueryService service;
	
	public EmployeeQueryController(EmployeeQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/hrm/employee")
	public ResponseEntity<?> getEmployeeList(EmployeeDTO.SearchEmployee dto) {
		
		List<Employee> list = service.getEmployeeList(dto);					
		
		List<EmployeeDTO.ResponseEmployee> dtoList = list.stream()
														 .map(e -> EmployeeDTO.ResponseEmployee.convert(e))
														 .collect(Collectors.toList()); 
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
}
