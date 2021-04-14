package com.like.workschedule.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.workschedule.boundary.ScheduleDTO;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.service.ScheduleQueryService;

@RestController
public class ScheduleQueryController {

	private ScheduleQueryService service;
	
	public ScheduleQueryController(ScheduleQueryService service) {
		this.service = service;
	}
	
	@GetMapping(value={"/grw/schedule"})
	public ResponseEntity<?> getScheduleList(@ModelAttribute ScheduleDTO.SearchSchedule searchCondition) {
						
		List<Schedule> workGroupList = service.getScheduleList(searchCondition);				
		
		List<ScheduleDTO.ScheduleResponse> dtoList = workGroupList.stream()
																  .map( r -> ScheduleDTO.convertResDTO(r))
																  .collect(Collectors.toList());
		
		return WebControllerUtil
				.getResponse(dtoList
							,dtoList.size()
							,dtoList.isEmpty()? false : true
							,dtoList.size() + "건 조회 되었습니다."
							,HttpStatus.OK);												
	}
}
