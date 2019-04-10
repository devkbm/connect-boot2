package com.like.todo.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.domain.repository.dto.TaskQueryDTO;
import com.like.todo.domain.repository.dto.TaskResultListDTO;
import com.like.todo.service.TaskCommandService;
import com.like.todo.service.TaskQueryService;

@RestController
public class TaskController {

	@Resource
	private TaskQueryService taskQueryService;
	
	@Resource
	private TaskCommandService taskCommandService;

	@RequestMapping(value={"/todo/taskgroups"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getTaskGroupList(@RequestParam(value="userId", required=true) String userId) {
			
		ResponseEntity<?> result = null;
		int resultSize = 0;		
		
		List<TaskGroup> list = taskQueryService.getTaskGroupList(userId);
		resultSize = list.size();
			
		result = WebControllerUtil.getResponse(list, 
				resultSize, 
				true, 
				String.format("%d 건 조회되었습니다.", resultSize), 
				HttpStatus.OK); 					
		
		return result;
	}	
	
	@RequestMapping(value={"/todo/taskgroups/new"}, method={RequestMethod.GET})
	public ResponseEntity<?> newTaskGroup() {
			
		ResponseEntity<?> res = null;
						
		taskCommandService.newTaskGroup();
								
		res = WebControllerUtil.getResponse(null,
				1, 
				true, 
				"생성되었습니다.", 
				HttpStatus.OK);
		
								 					
		return res;
	}
	
	@RequestMapping(value={"/todo/taskgroups"}, method={RequestMethod.POST,RequestMethod.PUT})
	public ResponseEntity<?> saveTaskGroupList(@RequestBody List<TaskGroup> taskGroupList, BindingResult result) {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} else {
			
			taskCommandService.saveTaskGroup(taskGroupList);
									
			res = WebControllerUtil.getResponse(null,
					taskGroupList.size(), 
					true, 
					String.format("%d 건 저장되었습니다.", taskGroupList.size()), 
					HttpStatus.OK);
		}
								 					
		return res;
	}
	
	@RequestMapping(value={"/todo/taskgroups"}, method={RequestMethod.DELETE})
	public ResponseEntity<?> deleteTerm(@RequestBody List<TaskGroup> taskGroupList, BindingResult result) {
			
		ResponseEntity<?> res = null;
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} else {
			
			taskCommandService.deleteTaskGroup(taskGroupList);
									
			res = WebControllerUtil.getResponse(null,
					taskGroupList.size(), 
					true, 
					String.format("%d 건 삭제되었습니다.", taskGroupList.size()), 
					HttpStatus.OK);
		}
								 					
		return res;
	}
	
	@RequestMapping(value={"/todo/tasks"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getTaskList(@ModelAttribute TaskQueryDTO taskQueryDTO) {
			
		ResponseEntity<?> result = null;
		
		List<TaskResultListDTO> list = taskQueryService.getTaskList(taskQueryDTO); 							
			
		result = WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 					
		
		return result;
	}
}
