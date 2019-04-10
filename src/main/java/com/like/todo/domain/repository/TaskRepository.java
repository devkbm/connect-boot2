package com.like.todo.domain.repository;

import java.util.List;

import com.like.todo.domain.model.Task;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.domain.repository.dto.TaskQueryDTO;
import com.like.todo.domain.repository.dto.TaskResultListDTO;

public interface TaskRepository {
	
	TaskGroup getTaskGroup(Long pkTaskGroup);
	
	List<TaskGroup> getTaskGroupList();
	
	List<TaskGroup> getTaskGroupList(String userId);
	
	void saveTaskGroup(TaskGroup taskGroup);
	
	void saveTaskGroup(List<TaskGroup> taskGroupList);
	
	void deleteTaskGroup(Long pkTaskGroup);
	
	void deleteTaskGroup(List<TaskGroup> taskGroupList);
	
	Task getTask(Long pkTask);
	
	List<Task> getTaskList();
	
	List<TaskResultListDTO> getTaskList(TaskQueryDTO taskQueryDTO);
	
	void saveTask(Task task);
	
	void deleteTask(Long pkTask);
}
