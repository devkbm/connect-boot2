package com.like.hrm.duty.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.hrm.duty.domain.repository.DutyCodeRepository;

@RestController
public class DutyCodeValidController {

	private DutyCodeRepository dutyCodeRepository;
	
	public DutyCodeValidController(DutyCodeRepository dutyCodeRepository) {
		this.dutyCodeRepository = dutyCodeRepository;
	}
	
	@GetMapping("/hrm/dutycode/{id}/valid")
	public ResponseEntity<?> getDutyCode(@PathVariable(value="id") String id) {
		
		boolean exist = dutyCodeRepository.existsById(id);
					
		return WebControllerUtil.getResponse(exist											
											,exist == true ? "사용가능한 근태 코드입니다." : "기존 근태 코드가 존재합니다."
											,HttpStatus.OK);
	}
	
}
