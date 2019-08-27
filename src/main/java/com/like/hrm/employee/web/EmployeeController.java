package com.like.hrm.employee.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/hrm/employee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable String id) {
				
		Employee emp = employeeService.getEmployee(id);  									
		
		return WebControllerUtil.getResponse(emp, 
											emp == null ? 0 : 1, 
											true, 
											String.format("%d 건 조회되었습니다.", emp == null ? 0 : 1), 
											HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/create"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO.NewEmployee dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
		
		log.info(dto.toString());
		Employee emp = new Employee(dto.getId(), dto.getName(), dto.getResidentRegistrationNumber());
		
		employeeService.saveEmployee(emp);
											 				
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/changedept"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveDeptChange(@RequestBody EmployeeDTO.NewDept dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
		Employee emp = employeeService.getEmployee(dto.getId());  	
		
		DeptChangeHistory deptChangeHistory = new DeptChangeHistory(emp, dto.getDeptType(), dto.getDeptCode(), dto.getFromDate(), dto.getToDate());
		
		employeeService.saveDeptChangeHistory(dto.getId(), deptChangeHistory);
											 				
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}
	
}
