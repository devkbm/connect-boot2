package com.like.commoncode.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.commoncode.boundary.CodeDTO;
import com.like.commoncode.boundary.CodeHierarchy;
import com.like.commoncode.domain.model.Code;
import com.like.commoncode.service.CommonCodeQueryService;
import com.like.core.web.util.WebControllerUtil;

@RestController
public class CommonCodeQueryController {

	private CommonCodeQueryService service;
	
	public CommonCodeQueryController(CommonCodeQueryService service) {
		this.service = service;
	}
	
	@GetMapping("/common/codetree") 
	public ResponseEntity<?> getCodeHierarchyList(@ModelAttribute CodeDTO.SearchCode searchCondition) {
							
		List<CodeHierarchy> list = service.getCodeHierarchyList(searchCondition);  						 						
		
		return WebControllerUtil
				.getResponse(list							
							,String.format("%d 건 조회되었습니다.", list.size())
							,HttpStatus.OK);
	}
	
	@GetMapping("/common/code") 
	public ResponseEntity<?> getCodeList(@ModelAttribute CodeDTO.SearchCode searchCondition) {
							
		List<Code> list = service.getCodeList(searchCondition);  						 						
		
		List<CodeDTO.SaveCode> dtoList = list.stream()
											 .map(e -> CodeDTO.SaveCode.convertDTO(e))
											 .collect(Collectors.toList());
		
		return WebControllerUtil
				.getResponse(dtoList							
							,String.format("%d 건 조회되었습니다.", dtoList.size())
							,HttpStatus.OK);
	}
}
