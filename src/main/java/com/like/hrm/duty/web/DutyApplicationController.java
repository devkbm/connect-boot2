package com.like.hrm.duty.web;

import java.util.List;
import java.util.stream.Collectors;

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
import com.like.hrm.duty.boundary.DutyApplicationDTO;
import com.like.hrm.duty.boundary.DutyApplicationInputLimitRuleDTO;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.model.DutyApplicationInputLimitRule;
import com.like.hrm.duty.service.DutyApplicationCommandService;
import com.like.hrm.duty.service.DutyApplicationInputLimitRuleService;
import com.like.hrm.duty.service.DutyApplicationQueryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DutyApplicationController {

	private DutyApplicationCommandService dutyApplicationCommandService;
	
	private DutyApplicationQueryService dutyApplicationQueryService;
	
	private DutyApplicationInputLimitRuleService dutyApplicationInputLimitRuleService;
	
	public DutyApplicationController(DutyApplicationCommandService dutyApplicationCommandService
									,DutyApplicationQueryService dutyApplicationQueryService
									,DutyApplicationInputLimitRuleService dutyApplicationInputLimitRuleService) {
		this.dutyApplicationCommandService = dutyApplicationCommandService;
		this.dutyApplicationQueryService = dutyApplicationQueryService;
		this.dutyApplicationInputLimitRuleService = dutyApplicationInputLimitRuleService;
	}
	
	@GetMapping("/hrm/dutyapplication")
	public ResponseEntity<?> getDutyApplicationList(DutyApplicationDTO.SearchDutyApplication dto) {
		
		List<DutyApplication> list = dutyApplicationQueryService.getDutyApplicationList(dto);					
		
		List<DutyApplicationDTO.SaveDutyApplication> dtoList = list.stream()
																   .map(e -> DutyApplicationDTO.SaveDutyApplication.convert(e))
																   .collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/dutyapplication/{id}")
	public ResponseEntity<?> getDutyApplication(@PathVariable(value="id") Long id) {
		
		DutyApplication entity = dutyApplicationCommandService.getDutyApplication(id);
					
		DutyApplicationDTO.SaveDutyApplication dto = DutyApplicationDTO.SaveDutyApplication.convert(entity);
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/dutyapplication"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveDutyApplication(@RequestBody DutyApplicationDTO.SaveDutyApplication dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		dutyApplicationCommandService.saveDutyApplication(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/dutyapplication/{id}")
	public ResponseEntity<?> deleteDutyApplication(@PathVariable(value="id") Long id) {				
																		
		dutyApplicationCommandService.deleteDutyApplication(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@GetMapping("/hrm/dutyapplication/limit")
	public ResponseEntity<?> getDutyApplicationLimitList() {
		
		List<DutyApplicationInputLimitRule> entityList = dutyApplicationInputLimitRuleService.getDutyApplicationInputLimitRule();					
					
		List<DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule> list = entityList.stream()
																								  .map(e -> DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule.convert(e))
																								  .collect(Collectors.toList());
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	
	@GetMapping("/hrm/dutyapplication/limit/{id}")
	public ResponseEntity<?> getDutyApplicationLimit(@PathVariable(value="id") Long id) {
		
		DutyApplicationInputLimitRule entity = dutyApplicationInputLimitRuleService.getDutyApplicationInputLimitRule(id);					
		
		DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule dto = DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule.convert(entity); 
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto == null ? 0 : 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/hrm/dutyapplication/limit"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveDutyApplicationLimit(@RequestBody DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.toString());
		} 
																	
		dutyApplicationInputLimitRuleService.saveDutyApplicationInputLimitRule(dto);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/hrm/dutyapplication/limit/{id}")
	public ResponseEntity<?> deleteDutyApplicationLimit(@PathVariable(value="id") Long id) {				
																		
		dutyApplicationInputLimitRuleService.deleteDutyApplicationInputLimitRule(id);						
								 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
		
}
