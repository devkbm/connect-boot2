package com.like.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.todo.boundary.TaskDTO;
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
	
	public void saveTaskGroup(TaskDTO.SaveTaskGroup dto) {
		TaskGroup entity = taskRepository.getTaskGroup(dto.getPkTaskGroup());
		
		entity.modify(dto.getTaskGroupName());
		
		taskRepository.saveTaskGroup(entity);	
	}	
		
	public void deleteTaskGroup(Long pkTaskGroup) {
		taskRepository.deleteTaskGroup(pkTaskGroup);
	}	
	
	public void saveTask(TaskDTO.SaveTask dto) {
		TaskGroup taskGroup = taskRepository.getTaskGroup(dto.getPkTaskGroup());
		Task entity = null;
		if (dto.getPkTask() == null) {
			entity = dto.newEntity(taskGroup);
		} else {
			entity = taskGroup.getTask(dto.getPkTask());
			dto.modifyEntity(entity);
		}
		
		taskRepository.saveTaskGroup(taskGroup);
	}
	
	public void deleteTask(Long pkTaskGroup, Long pkTask) {
		TaskGroup taskGroup = taskRepository.getTaskGroup(pkTaskGroup);	
		taskGroup.removeTask(pkTask);
	}
}
