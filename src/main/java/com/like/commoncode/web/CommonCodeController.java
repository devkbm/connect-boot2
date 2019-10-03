package com.like.commoncode.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.commoncode.boundary.CodeDTO;
import com.like.commoncode.boundary.CodeDTO.CodeHierarchy;
import com.like.commoncode.domain.model.Code;
import com.like.commoncode.infra.jparepository.CodeJpaRepository;
import com.like.commoncode.service.CommonCodeCommandService;
import com.like.commoncode.service.CommonCodeQueryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CommonCodeController {

	@Resource(name="codeJpaRepository")
	private CodeJpaRepository codeJpaRepository;
	
	@Resource
	private CommonCodeCommandService commonCodeCommandService;
	
	@Resource
	private CommonCodeQueryService commonCodeQueryService;			
	
	
	@GetMapping("/common/codetree") 
	public ResponseEntity<?> getCodeHierarchyList(@ModelAttribute CodeDTO.SearchCode searchCondition) {
							
		List<CodeHierarchy> list = commonCodeQueryService.getCodeHierarchyList(searchCondition);  						 						
		
		return WebControllerUtil.getResponse(list
											,list.size()
											,true
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/common/code") 
	public ResponseEntity<?> getCodeList(@ModelAttribute CodeDTO.SearchCode searchCondition) {
							
		List<Code> list = commonCodeQueryService.getCodeList(searchCondition);  						 						
		
		return WebControllerUtil.getResponse(list
											,list.size()
											,true
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/common/code/{id}") 
	public ResponseEntity<?> getCode(@PathVariable String id) {
								  						 					
		Code entity = commonCodeQueryService.getCode(id);
		
		CodeDTO.SaveCode dto = CodeDTO.convertDTO(entity);
		
		return WebControllerUtil.getResponse(dto
											,1
											,true
											,String.format("%d 건 조회되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	
	@RequestMapping(value={"/common/code"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveCode(@RequestBody CodeDTO.SaveCode dto, BindingResult result) {			
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 									
				
		commonCodeCommandService.saveCode(dto);		
											 				
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}	
		
	@DeleteMapping("/common/code/{id}")
	public ResponseEntity<?> delCode(@PathVariable(value="id") String id) {						
												
		commonCodeCommandService.deleteCode(id);
								 						
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	
}
