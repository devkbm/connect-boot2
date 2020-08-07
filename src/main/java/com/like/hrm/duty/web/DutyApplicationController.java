package com.like.hrm.duty.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.service.DutyApplicationCommandService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DutyApplicationController {

	private DutyApplicationCommandService dutyApplicationCommandService;
	
	public DutyApplicationController(DutyApplicationCommandService dutyApplicationCommandService) {
		this.dutyApplicationCommandService = dutyApplicationCommandService;
	}
	
	@GetMapping("/hrm/dutyapplication/{id}")
	public ResponseEntity<?> getDutyApplication(@PathVariable(value="id") String id) {
		
		DutyApplication entity = dutyApplicationCommandService.getDutyApplication(id);
					
		return WebControllerUtil.getResponse(entity											
											,String.format("%d 건 조회되었습니다.", entity == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/dutyapplication"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveDutyApplication(@RequestBody DutyApplication dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		dutyApplicationCommandService.saveDutyApplication(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/dutyapplication/{id}")
	public ResponseEntity<?> deleteDutyCode(@PathVariable(value="id") String id) {				
																		
		dutyApplicationCommandService.deleteDutyApplication(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
