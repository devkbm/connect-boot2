package com.like.survey.web;

import java.util.List;

import javax.validation.Valid;

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
import com.like.survey.boundary.SurveyFormDTO;
import com.like.survey.domain.model.SurveyForm;
import com.like.survey.domain.model.SurveyItem;
import com.like.survey.service.SurveyQueryService;
import com.like.survey.service.SurveyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SurveyController {

	private SurveyService surveyService;
	
	private SurveyQueryService surveyQueryService;
	
	public SurveyController(SurveyService surveyService, SurveyQueryService surveyQueryService) {
		this.surveyService = surveyService;		
		this.surveyQueryService = surveyQueryService;
	}
	
	@GetMapping("/survey/form")
	public ResponseEntity<?> getSurveyFormList(SurveyFormDTO.SearchSurveyForm dto) {				
		
		List<SurveyForm> list = surveyQueryService.getSurveyFormList(dto); 		
								
		return WebControllerUtil.getResponse(list
											,list.size()
											,true
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
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
	
	
	@RequestMapping(value={"/survey/form"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody SurveyFormDTO.SaveSurveyForm dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
																			
		surveyService.saveSurveyForm(dto);			
										 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/survey/form/{id}")
	public ResponseEntity<?> deleteSurveyForm(@PathVariable(value="id") Long formId) {				
		
		surveyService.deleteSurveyForm(formId); 		
								
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/survey/form/{formId}/item/{itemId}")
	public ResponseEntity<?> getSurveyItem(@PathVariable(value="formId") Long formId
										  ,@PathVariable(value="itemId") Long itemId) {				
		
		SurveyItem surveryForm = surveyService.getSurveyItem(formId, itemId); 		
								
		return WebControllerUtil.getResponse(surveryForm
											,surveryForm != null ? 1 : 0
											,true
											,String.format("%d 건 조회되었습니다.", surveryForm != null ? 1 : 0)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/survey/form/item"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody SurveyFormDTO.SaveSurveyItem dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
																			
		surveyService.saveSurveyItem(dto);			
										 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/survey/form/{formId}/item/{itemId}")
	public ResponseEntity<?> deleteSurveyForm(@PathVariable(value="formId") Long formId
			  								 ,@PathVariable(value="itemId") Long itemId) {				
		
		surveyService.deleteSurveyItem(formId, itemId); 		
								
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
}
