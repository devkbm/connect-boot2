package com.like.hrm.code.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.code.domain.repository.HrmTypeDetailCodeRepository;
import com.like.hrm.code.domain.repository.HrmTypeRepository;

@RestController
public class HrmCodeValidController {
	
	private HrmTypeRepository repository;
	private HrmTypeDetailCodeRepository hrmTypeDetailCodeRepository;
	
	public HrmCodeValidController(HrmTypeRepository repository
								 ,HrmTypeDetailCodeRepository hrmTypeDetailCodeRepository) {
		this.repository = repository;
		this.hrmTypeDetailCodeRepository = hrmTypeDetailCodeRepository;
	}
	
	@GetMapping("/hrm/hrmtype/{id}/valid")
	public ResponseEntity<?> getHrmType(@PathVariable(value="id") String id) {
		
		boolean exist = repository.existsById(id);
					
		return WebControllerUtil.getResponse(exist											
											,exist ? "중복된 인사유형 코드가 있습니다." : "사용가능한 코드입니다."
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/typedetailcode/{id}/valid")
	public ResponseEntity<?> getTypeDetailCode(@PathVariable(value="id") String id) {
		
		boolean exist = hrmTypeDetailCodeRepository.existsById(id);
					
		return WebControllerUtil.getResponse(exist
											,exist ? "중복된 인사유형 코드가 있습니다." : "사용가능한 코드입니다."
											,HttpStatus.OK);
	}
	
	/*
	@GetMapping("/common/dept/{id}/valid")
	public ResponseEntity<?> getValidateDeptDuplication(@PathVariable String id) {
							
		Boolean exist = deptService.isDept(id);  	
						
		return WebControllerUtil
				.getResponse(exist								
							,exist ? "중복된 부서 코드가 있습니다." : "사용가능한 부서 코드입니다." 
							,HttpStatus.OK);
	}
	
	 * 
	 */
}
