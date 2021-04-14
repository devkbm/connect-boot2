package com.like.todo.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.util.SessionUtil;
import com.like.core.web.exception.ControllerException;
import com.like.core.web.util.WebControllerUtil;
import com.like.todo.boundary.TaskDTO;
import com.like.todo.domain.model.Task;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.service.TaskCommandService;
import com.like.todo.service.TaskQueryService;

@RestController
public class TaskController {
	
	private TaskQueryService taskQueryService;
		
	private TaskCommandService taskCommandService;

	public TaskController(TaskQueryService taskQueryService
						 ,TaskCommandService taskCommandService) {
		this.taskQueryService = taskQueryService;
		this.taskCommandService = taskCommandService;
	}
		
	@GetMapping("/todo/taskgroup/mylist")
	public ResponseEntity<?> getTaskGroupList() {
						
		String userId = SessionUtil.getUserId();
		
		List<TaskGroup> list = taskQueryService.getTaskGroupList(userId);			 					
		
		return WebControllerUtil.getResponse(list
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}	
		
	@PostMapping("/todo/taskgroup/new")
	public ResponseEntity<?> newTaskGroup() {
										
		taskCommandService.newTaskGroup();										
								 					
		return WebControllerUtil.getResponse(null
										    ,"생성되었습니다."
										    ,HttpStatus.OK);
	}
		
	@PostMapping("/todo/taskgroup")
	public ResponseEntity<?> saveTaskGroup(@RequestBody TaskDTO.SaveTaskGroup dto, BindingResult result) {
							
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
			
		taskCommandService.saveTaskGroup(dto);
																				 			
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@DeleteMapping("/todo/taskgroup/{id}")
	public ResponseEntity<?> deleteTerm(@PathVariable("id") Long id) {							
			
		taskCommandService.deleteTaskGroup(id);
											 				
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@GetMapping("/todo/taskgroup/{id}/task")
	public ResponseEntity<?> getTaskList(@PathVariable("id") Long id) {				
		
		List<Task> list = taskQueryService.getTaskGroup(id).getTaskList();
		
		List<TaskDTO.SaveTask> dtoList = list.stream().map(e -> TaskDTO.SaveTask.convert(e)).collect(Collectors.toList()); 											
		
		return WebControllerUtil.getResponse(dtoList
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
	
	@PostMapping("/todo/taskgroup/task")
	public ResponseEntity<?> saveTask(@RequestBody TaskDTO.SaveTask dto, BindingResult result) {
							
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
			
		taskCommandService.saveTask(dto);
																				 			
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/todo/taskgroup/{id}/task/{id2}")
	public ResponseEntity<?> deleteTask(@PathVariable("id") Long id
									   ,@PathVariable("id") Long id2) {							
			
		taskCommandService.deleteTask(id, id2);
											 				
		return WebControllerUtil.getResponse(null
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
}
