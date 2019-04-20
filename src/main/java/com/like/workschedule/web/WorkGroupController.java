package com.like.workschedule.web;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.team.domain.model.TeamMember;
import com.like.team.dto.TeamDTO;
import com.like.team.domain.model.Team;
import com.like.team.domain.model.TeamDTOAssembler;
import com.like.team.service.TeamService;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.like.user.dto.UserDTO;
import com.like.user.service.UserService;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.model.WorkScheduleDTOAssembler;
import com.like.workschedule.domain.repository.ScheduleRepository;
import com.like.workschedule.dto.WorkDTO;
import com.like.workschedule.service.WorkGroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WorkGroupController {
	
	@Resource(name="scheduleJpaRepository")
	ScheduleRepository scheduleRepository;	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	WorkGroupService workGroupService;
			
	@GetMapping(value={"/grw/workgroup"})
	public ResponseEntity<?> getWorkGroupList(@ModelAttribute WorkDTO.SearchCondition searchCondition) {
						
		List<WorkGroup> workGroupList = workGroupService.getWorkGroupList(searchCondition);				
		
		return WebControllerUtil.getResponse(workGroupList,
				workGroupList.size(), 
				workGroupList.isEmpty()? false : true,
				workGroupList.size() + "건 조회 되었습니다.",
				HttpStatus.OK);												
	}
	
	@GetMapping(value={"/grw/workgroup/{id}"})
	public ResponseEntity<?> getWorkGroup(@PathVariable(value="id") Long id) {
						
		WorkGroup entity = workGroupService.getWorkGroup(id);							
		
		log.info(id.toString());		
		log.info(id.toString());
		log.info(id.toString());
		log.info(id.toString());
		
		return WebControllerUtil.getResponse(entity,
				entity == null ? 0 : 1, 
				entity == null ? false : true,
				"조회 되었습니다.",
				HttpStatus.OK);													
	}
	
	@RequestMapping(value={"/grw/workgroup"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkDTO.WorkGroupSave dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
		
		WorkGroup entity = WorkScheduleDTOAssembler.toEntity(scheduleRepository, dto);
		
		//workGroupService.saveWorkGroup(entity);		
		if (dto.getUserList() != null) {
			List<User> user = userRepository.getUserList(dto.getUserList());
			workGroupService.saveWorkGroupMember(entity, user);
		}
		
		//log.info(user.getUserId());
		//log.info(user.getUserId());
		
		return WebControllerUtil.getResponse(entity,
				entity != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", entity != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	
	@GetMapping(value={"/grw/schedule"})
	public ResponseEntity<?> getScheduleList(@ModelAttribute WorkDTO.ScheduleSearch searchCondition) {
						
		List<Schedule> workGroupList = workGroupService.getScheduleList(searchCondition);				
		
		return WebControllerUtil.getResponse(workGroupList,
				workGroupList.size(), 
				workGroupList.isEmpty()? false : true,
				workGroupList.size() + "건 조회 되었습니다.",
				HttpStatus.OK);												
	}
	
	@GetMapping(value={"/grw/schedule/{id}"})
	public ResponseEntity<?> getSchedule(@PathVariable(value="id") Long id) {
						
		Schedule entity = workGroupService.getSchedule(id);							
		
		return WebControllerUtil.getResponse(entity,
				entity == null ? 0 : 1, 
				entity == null ? false : true,
				"조회 되었습니다.",
				HttpStatus.OK);													
	}
	
	@RequestMapping(value={"/grw/schedule"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveWorkGroup(@Valid @RequestBody WorkDTO.ScheduleSave dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
		
		Schedule entity = WorkScheduleDTOAssembler.toEntity(scheduleRepository, dto);
		
		workGroupService.saveSchedule(entity);		
										 					
		return WebControllerUtil.getResponse(entity,
				entity != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", entity != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
}
