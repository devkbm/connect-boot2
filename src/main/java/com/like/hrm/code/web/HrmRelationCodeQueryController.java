package com.like.hrm.code.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.hrm.code.boundary.HrmRelationCodeDTO;
import com.like.hrm.code.service.HrmRelationCodeQueryService;

@RestController
public class HrmRelationCodeQueryController {

	private HrmRelationCodeQueryService service;
	
	public HrmRelationCodeQueryController(HrmRelationCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/hrm/hrmrelation")
	public ResponseEntity<?> getHrmRelationCode(HrmRelationCodeDTO.SearchHrmRelationCode dto) {
						
		List<?> list = service.getHrmRelationCodeList(dto);
					
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
}
