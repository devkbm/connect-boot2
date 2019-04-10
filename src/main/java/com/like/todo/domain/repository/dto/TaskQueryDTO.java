package com.like.todo.domain.repository.dto;

import java.time.LocalDate;

import org.springframework.util.StringUtils;

import com.like.todo.domain.model.QTask;
import com.like.todo.domain.model.QTaskGroup;
import com.querydsl.core.BooleanBuilder;

public class TaskQueryDTO {

	private final QTaskGroup qTaskGroup = QTaskGroup.taskGroup;
	private final QTask qTask = QTask.task1;
	private BooleanBuilder builder = new BooleanBuilder();
		
	String userId;
	
	String task;		
		
	Boolean isCompleted;
		
    LocalDate dueDate;
	
	public BooleanBuilder getQueryFilter() {		
		
		if (StringUtils.hasText(this.userId))
			builder.and(qTaskGroup.modifiedBy.eq(userId));
		
		if (StringUtils.hasText(this.task))
			builder.and(qTask.task.like("%"+task+"%"));
		
		if (this.isCompleted != null)
			builder.and(qTask.isCompleted.eq(isCompleted));				 		
		
		return builder;
	}
}
