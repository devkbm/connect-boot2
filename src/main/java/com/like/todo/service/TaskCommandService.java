package com.like.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.todo.domain.model.Task;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.domain.repository.TaskRepository;

@Service
@Transactional
public class TaskCommandService {
		
	private TaskRepository taskRepository;
	
	public TaskCommandService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;		
	}
	
	public void newTaskGroup() {
		TaskGroup taskGroup = new TaskGroup("기본 일정");
		taskRepository.saveTaskGroup(taskGroup);
	}
	
	public void saveTaskGroup(TaskGroup taskGroup) {
		taskRepository.saveTaskGroup(taskGroup);
	}
	
	public void saveTaskGroup(List<TaskGroup> taskGroupList) {
		taskRepository.saveTaskGroup(taskGroupList);
	}
	
	public void deleteTaskGroup(Long pkTaskGroup) {
		taskRepository.deleteTaskGroup(pkTaskGroup);
	}
	
	public void deleteTaskGroup(List<TaskGroup> taskGroupList) {
		taskRepository.deleteTaskGroup(taskGroupList);
	}

	public void saveTask(Task task) {
		taskRepository.saveTask(task);
	}
	
	public void deleteTask(Long pkTask) {
		taskRepository.deleteTask(pkTask);
	}

}
