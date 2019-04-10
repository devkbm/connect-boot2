package com.like.todo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.todo.domain.model.Task;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.domain.repository.TaskRepository;
import com.like.todo.domain.repository.dto.TaskQueryDTO;
import com.like.todo.domain.repository.dto.TaskResultListDTO;

@Service
@Transactional(readOnly=true)
public class TaskQueryService {
	
	@Resource(name="taskJpaRepository")
	private TaskRepository taskRepository;
	
	
	public TaskGroup getTaskGroup(Long pkTaskGroup) {		
		return taskRepository.getTaskGroup(pkTaskGroup);
	}
	
	public List<TaskGroup> getTaskGroupList() {
		return taskRepository.getTaskGroupList();
	}
	
	public List<TaskGroup> getTaskGroupList(String userId) {
		return taskRepository.getTaskGroupList(userId);
	}
	
	public Task getTask(Long pkTask) {
		return taskRepository.getTask(pkTask);
	}
	
	public List<Task> getTaskList() {
		return taskRepository.getTaskList();
	}
	
	public List<TaskResultListDTO> getTaskList(TaskQueryDTO taskQueryDTO) {
		return taskRepository.getTaskList(taskQueryDTO);
	}

}
