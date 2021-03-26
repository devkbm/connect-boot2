package com.like.commoncode.web;

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
import com.like.commoncode.boundary.CodeDTO;
import com.like.commoncode.domain.model.Code;
import com.like.commoncode.service.CommonCodeCommandService;

@RestController
public class CommonCodeController {
	
	private CommonCodeCommandService service;			
		
	public CommonCodeController(CommonCodeCommandService service) {
		this.service = service;		
	}	
	
	@GetMapping("/common/code/{id}") 
	public ResponseEntity<?> getCode(@PathVariable String id) {
								  						 					
		Code entity = service.getCode(id);
		
		CodeDTO.SaveCode dto = CodeDTO.SaveCode.convertDTO(entity);
		
		return WebControllerUtil
				.getResponse(dto							
							,String.format("%d 건 조회되었습니다.", 1)
							,HttpStatus.OK);
	}
	
	
	@RequestMapping(value={"/common/code"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody CodeDTO.SaveCode dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 												
		
		service.saveCode(dto);		
											 				
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}	
		
	@DeleteMapping("/common/code/{id}")
	public ResponseEntity<?> delCode(@PathVariable(value="id") String id) {						
												
		service.deleteCode(id);
								 						
		return WebControllerUtil
				.getResponse(null							
							,String.format("%d 건 삭제되었습니다.", 1)
							,HttpStatus.OK);
	}
	
	
}
