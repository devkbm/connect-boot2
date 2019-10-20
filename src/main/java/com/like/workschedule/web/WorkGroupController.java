package com.like.workschedule.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.like.common.util.SessionUtil;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.workschedule.boundary.ScheduleDTO;
import com.like.workschedule.boundary.WorkDTO;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.service.WorkGroupQueryService;
import com.like.workschedule.service.WorkGroupService;

@RestController
public class WorkGroupController {	
		
	private WorkGroupService workGroupService;	
			
	private WorkGroupQueryService workGroupQueryService;
		
	public WorkGroupController(WorkGroupService workGroupService
							  ,WorkGroupQueryService workGroupQueryService) {
		this.workGroupService = workGroupService;
		this.workGroupQueryService = workGroupQueryService;
	}

	@GetMapping(value={"/grw/workgroup"})
	public ResponseEntity<?> getWorkGroupList(@ModelAttribute WorkDTO.SearchWorkGroup searchCondition) {
						
		List<WorkGroup> workGroupList = workGroupQueryService.getWorkGroupList(searchCondition);				
		
		return WebControllerUtil
				.getResponse(workGroupList
							,workGroupList.size()
							,workGroupList.isEmpty()? false : true
							,workGroupList.size() + "건 조회 되었습니다."
							,HttpStatus.OK);												
	}
	
	@GetMapping(value={"/grw/myworkgroup"})
	public ResponseEntity<?> getWorkGroupList() {
						
		String sessionId = SessionUtil.getUserId(); // SecurityContextHolder.getContext().getAuthentication().getName();
		
		List<WorkGroup> workGroupList = workGroupService.getMyWorkGroupList(sessionId);				
		
		return WebControllerUtil
				.getResponse(workGroupList
							,workGroupList.size()
							,workGroupList.isEmpty()? false : true
							,workGroupList.size() + "건 조회 되었습니다."
							,HttpStatus.OK);												
	}
	
	@GetMapping(value={"/grw/workgroup/{id}"})
	public ResponseEntity<?> getWorkGroup(@PathVariable(value="id") Long id) {
						
		WorkGroup entity = workGroupService.getWorkGroup(id);										
		
		WorkDTO.SaveWorkGroup dto = WorkDTO.convertDTO(entity);
		
		return WebControllerUtil
				.getResponse(dto
							,dto == null ? 0 : 1
							,dto == null ? false : true
							,"조회 되었습니다."
							,HttpStatus.OK);													
	}
	
	@RequestMapping(value={"/grw/workgroup"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkDTO.SaveWorkGroup dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
				
		workGroupService.saveWorkGroup(dto);		
		
		return WebControllerUtil
				.getResponse(dto
							,dto != null ? 1 : 0
							,true
							,String.format("%d 건 저장되었습니다.", dto != null ? 1 : 0)
							,HttpStatus.OK);
	}
	
	
	@GetMapping(value={"/grw/schedule"})
	public ResponseEntity<?> getScheduleList(@ModelAttribute ScheduleDTO.SearchSchedule searchCondition) {
						
		List<Schedule> workGroupList = workGroupQueryService.getScheduleList(searchCondition);				
		
		List<ScheduleDTO.ScheduleResponse> dtoList = workGroupList.stream().map( r -> ScheduleDTO.convertResDTO(r)).collect(Collectors.toList());
		
		return WebControllerUtil
				.getResponse(dtoList
							,dtoList.size()
							,dtoList.isEmpty()? false : true
							,dtoList.size() + "건 조회 되었습니다."
							,HttpStatus.OK);												
	}
	
	@GetMapping(value={"/grw/schedule/{id}"})
	public ResponseEntity<?> getSchedule(@PathVariable(value="id") Long id) {
						
		Schedule entity = workGroupService.getSchedule(id);							
		
		ScheduleDTO.ScheduleResponse dto = ScheduleDTO.convertResDTO(entity);
		
		return WebControllerUtil
				.getResponse(dto
							,dto == null ? 0 : 1
							,dto == null ? false : true
							,"조회 되었습니다."
							,HttpStatus.OK);													
	}
	
	@RequestMapping(value={"/grw/schedule"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody ScheduleDTO.SaveSchedule dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
					
		workGroupService.saveSchedule(dto);		
										 					
		return WebControllerUtil
				.getResponse(dto
							,dto != null ? 1 : 0
							,true
							,String.format("%d 건 저장되었습니다.", dto != null ? 1 : 0)
							,HttpStatus.OK);
	}
	
	@DeleteMapping(value={"/grw/schedule/{id}"})
	public ResponseEntity<?> deleteSchedule(@PathVariable(value="id") Long id) {
						
		workGroupService.deleteSchedule(id);							
				
		return WebControllerUtil
				.getResponse(null
							,1
							,true
							,"삭제 되었습니다."
							,HttpStatus.OK);													
	}
	
}
