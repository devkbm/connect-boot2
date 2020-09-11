package com.like.hrm.employee.web;

import java.util.List;

import javax.validation.Valid;

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
import com.like.hrm.employee.domain.model.SchoolCareer;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.License;
import com.like.hrm.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/hrm/employee")
	public ResponseEntity<?> getEmployeeList(EmployeeDTO.SearchEmployee dto) {
		
		List<Employee> list = employeeService.getEmployeeList(dto);					
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/employee/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable String id) {
				
		Employee emp = employeeService.getEmployee(id);  									
		
		return WebControllerUtil
				.getResponse(emp											
							,String.format("%d 건 조회되었습니다.", emp == null ? 0 : 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/create"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> newEmployee(@RequestBody @Valid EmployeeDTO.NewEmployee dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
						
		employeeService.newEmployee(dto);
											 				
		return WebControllerUtil
				.getResponse(null											
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveEmployee(@RequestBody EmployeeDTO.SaveEmployee dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
						
		employeeService.saveEmployee(dto);
											 				
		return WebControllerUtil
				.getResponse(null											
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/changedept"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveDeptChange(@RequestBody EmployeeDTO.NewDept dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
				
		employeeService.saveDeptChangeHistory(dto);
											 				
		return WebControllerUtil
				.getResponse(null											
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/changejob"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveJobChange(@RequestBody EmployeeDTO.NewJob dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
				
		employeeService.saveJobChangeHistory(dto);
											 				
		return WebControllerUtil
				.getResponse(null											
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/changestatus"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveStatusChange(@RequestBody EmployeeDTO.NewStatus dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
				
		employeeService.saveStatusChangeHistory(dto);
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}	
	
}
