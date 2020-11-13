package com.like.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.todo.domain.model.TaskGroup;
import com.like.todo.domain.repository.TaskRepository;

@Service
@Transactional(readOnly=true)
public class TaskQueryService {
	
	private TaskRepository taskRepository;
	
	public TaskQueryService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public TaskGroup getTaskGroup(Long pkTaskGroup) {		
		return taskRepository.getTaskGroup(pkTaskGroup);
	}	
	
	public List<TaskGroup> getTaskGroupList(String userId) {
		return taskRepository.getTaskGroupList(userId);
	}
		
}
