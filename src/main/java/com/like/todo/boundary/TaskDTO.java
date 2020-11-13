package com.like.todo.boundary;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.util.StringUtils;

import com.like.todo.domain.model.QTask;
import com.like.todo.domain.model.QTaskGroup;
import com.like.todo.domain.model.Task;
import com.like.todo.domain.model.TaskGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Builder;
import lombok.Data;

public class TaskDTO {

	@Data
	public static class SaveTaskGroup implements Serializable {
		
		private static final long serialVersionUID = 3162668012726732820L;

		Long pkTaskGroup;
		
		String taskGroupName;
		
		public void modifyEntity(TaskGroup entity) {
			entity.modify(taskGroupName);
		}
	}
	
	@Data
	public static class SearchTask implements Serializable {
		
		private static final long serialVersionUID = -3386038568613139989L;
		private final QTaskGroup qTaskGroup = QTaskGroup.taskGroup;
		private final QTask qTask = QTask.task1;		
			
		String userId;
		
		String task;		
			
		Boolean isCompleted;
			
	    LocalDate dueDate;
		
		public BooleanBuilder getQueryFilter() {		
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeMenGroupCode(task));
			
			if (StringUtils.hasText(this.userId))
				builder.and(qTaskGroup.modifiedBy.eq(userId));
			
			if (StringUtils.hasText(this.task))
				builder.and(qTask.task.like("%"+task+"%"));
			
			if (this.isCompleted != null)
				builder.and(qTask.isCompleted.eq(isCompleted));				 		
			
			return builder;
		}
		
		private BooleanExpression likeMenGroupCode(String task) {
			if (StringUtils.isEmpty(task)) {
				return null;
			}
			
			return qTask.task.like("%"+task+"%");
		}
	}
	
	@Builder
	@Data
	public static class SaveTask implements Serializable {			

		private static final long serialVersionUID = 3295600355632998097L;

		Long pkTaskGroup;
		
		Long pkTask;
		
		String task;
		
		boolean isCompleted;
		
		LocalDate dueDate;
		
		String comments;
		
		public Task newEntity(TaskGroup taskGroup) {
			return Task.builder()
					   .taskGroup(taskGroup)
					   .task(task)
					   .dueDate(dueDate)
					   .comments(comments)
					   .build();	
		}
		
		public void modifyEntity(Task entity) {
			entity.modify(task, isCompleted, dueDate, comments);
		}
		
		public static SaveTask convert(Task entity) {
			return SaveTask.builder()
					       .pkTaskGroup(entity.getTaskGroup().getPkTaskGroup())
					       .pkTask(entity.getPkTask())
					       .task(entity.getTask())
					       .isCompleted(entity.isCompleted())
					       .dueDate(entity.getDueDate())
					       .comments(entity.getComments())
						   .build();	
		}
	}
	
}
