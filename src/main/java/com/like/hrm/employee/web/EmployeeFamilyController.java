package com.like.hrm.employee.web;

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
import com.like.hrm.employee.domain.model.Family;
import com.like.hrm.employee.service.EmployeeFamilyService;

@RestController
public class EmployeeFamilyController {

	private EmployeeFamilyService service;
		
	public EmployeeFamilyController(EmployeeFamilyService service) {
		this.service = service;	
	}
	
	@GetMapping("/hrm/employee/{empId}/family/{id}")
	public ResponseEntity<?> getFamily(@PathVariable String empId
									  ,@PathVariable Long id) {
				
		Family entity = service.getFamily(empId, id);  									
				
		EmployeeDTO.SaveFamily dto = EmployeeDTO.SaveFamily.convert(entity) ;
		
		return WebControllerUtil
				.getResponse(dto											
							,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/family"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveFamily(@RequestBody EmployeeDTO.SaveFamily dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
				
		service.saveFamily(dto);
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
}
