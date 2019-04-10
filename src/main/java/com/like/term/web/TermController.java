package com.like.term.web;

import java.util.List;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.term.domain.model.TermDictionary;
import com.like.term.dto.TermDTO;
import com.like.term.service.TermService;

@RestController
public class TermController {

	@Resource
	private TermService termService;
	
	private static final Logger log = LoggerFactory.getLogger(TermController.class);		
		
	@GetMapping("/common/terms/{id}")
	public ResponseEntity<?> getTerm(@PathVariable(value="id") Long id) {
		
		TermDictionary term = termService.getTerm(id);								
		
		return WebControllerUtil.getResponse(term, 
				1, 
				true, 
				String.format("%d 건 조회되었습니다.", 1), 
				HttpStatus.OK);
	}	
	
	@RequestMapping(value={"/common/terms"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getTermList(TermDTO.QueryCondition contidion) {
				
		List<TermDictionary> list = termService.getTermList(contidion); 							
							
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}	
	
	@RequestMapping(value={"/common/terms"}, method={RequestMethod.POST,RequestMethod.PUT})
	public ResponseEntity<?> saveTerm(@RequestBody TermDictionary term, BindingResult result) {
					
		if ( result.hasErrors()) {
			//throw new IllegalArgumentException();
			throw new ControllerException("오류");
		}
		
		log.info(term.toString());
		
		termService.saveTerm(term);										
								 					
		return WebControllerUtil.getResponse(null,
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	
	}
		
	
		
	@RequestMapping(value={"/common/terms/{id}"}, method=RequestMethod.DELETE) 
	public ResponseEntity<?> delTerm(@PathVariable(value="id") Long id) {
								
		termService.deleteTerm(id);										
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}		
	
}