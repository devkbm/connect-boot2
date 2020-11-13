package com.like.todo.domain.repository;

import java.util.List;

import com.like.todo.domain.model.TaskGroup;

public interface TaskRepository {
	
	List<TaskGroup> getTaskGroupList(String userId);
	
	TaskGroup getTaskGroup(Long pkTaskGroup);		
	
	void saveTaskGroup(TaskGroup taskGroup);	
	
	void deleteTaskGroup(Long pkTaskGroup);	
}
