package com.like.hrm.code.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.domain.repository.HrmCodeRepository;
import com.like.hrm.code.service.HrmCodeQueryService;
import com.like.hrm.code.service.HrmCodeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class HrmCodeValidController {
	
	private HrmCodeRepository hrmCodeRepository;

	public HrmCodeValidController(HrmCodeRepository hrmCodeRepository) {
		this.hrmCodeRepository = hrmCodeRepository;
	}
	
	@GetMapping("/hrm/hrmtype/{id}/valid")
	public ResponseEntity<?> getHrmType(@PathVariable(value="id") String id) {
		
		boolean exist = hrmCodeRepository.isHrmType(id);
					
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
