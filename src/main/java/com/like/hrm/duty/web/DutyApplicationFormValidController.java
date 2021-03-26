package com.like.hrm.duty.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.duty.domain.repository.DutyApplicationRepository;

@RestController
public class DutyApplicationFormValidController {

	private DutyApplicationRepository repository;
	
	public DutyApplicationFormValidController(DutyApplicationRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/hrm/dutyapplication/{id}/valid")
	public ResponseEntity<?> getDutyCode(@PathVariable(value="id") Long id) {
		
		boolean exist = repository.existsById(id);
					
		return WebControllerUtil.getResponse(exist											
											,exist == true ? "사용가능한 근태 코드입니다." : "기존 근태 코드가 존재합니다."
											,HttpStatus.OK);
	}
}
