package com.like.workschedule.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.util.SessionUtil;
import com.like.core.web.util.WebControllerUtil;
import com.like.workschedule.boundary.WorkDTO;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.service.WorkGroupQueryService;

@RestController
public class WorkGroupQueryController {

	private WorkGroupQueryService service;
	
	public WorkGroupQueryController(WorkGroupQueryService service) {
		this.service = service;		
	}
	
	@GetMapping(value={"/grw/workgroup"})
	public ResponseEntity<?> getWorkGroupList(@ModelAttribute WorkDTO.SearchWorkGroup searchCondition) {
						
		List<WorkGroup> workGroupList = service.getWorkGroupList(searchCondition);				
		
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
		
		List<WorkGroup> workGroupList = service.getMyWorkGroupList(sessionId);				
		
		return WebControllerUtil
				.getResponse(workGroupList
							,workGroupList.size()
							,workGroupList.isEmpty()? false : true
							,workGroupList.size() + "건 조회 되었습니다."
							,HttpStatus.OK);												
	}
}
