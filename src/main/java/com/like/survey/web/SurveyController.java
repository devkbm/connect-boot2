package com.like.survey.web;

import javax.validation.Valid;

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
import com.like.menu.boundary.MenuGroupDTO;
import com.like.survey.domain.model.SurveyForm;
import com.like.survey.service.SurveyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SurveyController {

	private SurveyService surveyService;
	
	public SurveyController(SurveyService surveyService) {
		this.surveyService = surveyService;		
	}
	
	@GetMapping("/survey/form/{id}")
	public ResponseEntity<?> getSurveyForm(@PathVariable(value="id") Long formId) {				
		
		SurveyForm surveryForm = surveyService.getSurveyForm(formId); 		
								
		return WebControllerUtil.getResponse(surveryForm
											,surveryForm != null ? 1 : 0
											,true
											,String.format("%d 건 조회되었습니다.", surveryForm != null ? 1 : 0)
											,HttpStatus.OK);
	}
	
	/*
	@RequestMapping(value={"/survey/form"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO.SaveMenuGroup dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
																			
		menuCommandService.saveMenuGroup(dto);			
										 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}*/
	
}
