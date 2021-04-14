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

import com.like.core.web.exception.ControllerException;
import com.like.core.web.util.WebControllerUtil;
import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.License;
import com.like.hrm.employee.service.EmployeeLicenseService;

@RestController
public class EmployeeLicenseController {

	private EmployeeLicenseService service;
		
	public EmployeeLicenseController(EmployeeLicenseService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/employee/{empId}/license/{id}")
	public ResponseEntity<?> getLicense(@PathVariable String empId,
										@PathVariable Long id) {
				
		License license = service.getLicense(empId, id);  									
		
		return WebControllerUtil
				.getResponse(license											
							,String.format("%d 건 조회되었습니다.", license == null ? 0 : 1)
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/employee/license"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveLicense(@RequestBody EmployeeDTO.SaveLicense dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
				
		service.saveLicense(dto);
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
}
