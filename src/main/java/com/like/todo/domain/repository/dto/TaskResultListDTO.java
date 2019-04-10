package com.like.todo.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TaskResultListDTO implements Serializable {
	
	private static final long serialVersionUID = 2305884687622067526L;

	/**
	 * 생성일자
	 */
	LocalDateTime sysDt;	
	
	/**
	 * 생성유저		
	 */
	String sysUser;	
	
	/**
	 * 수정일자			
	 */
	LocalDateTime updDt;
	
	/**
	 * 수정유저			
	 */
	String updUser;	
	
	Long pkTaskGroup;	
	
	String userId;
		
	String taskGroupName;
	
	Long pkTask;	
	
	String task;		
		
	boolean isCompleted;
		
    LocalDate dueDate;
		
	String comments;

	@QueryProjection
	public TaskResultListDTO(LocalDateTime sysDt, String sysUser, LocalDateTime updDt, String updUser, Long pkTaskGroup, 
			String taskGroupName, Long pkTask, String task, boolean isCompleted, LocalDate dueDate, String comments) {
		super();
		this.sysDt = sysDt;
		this.sysUser = sysUser;
		this.updDt = updDt;
		this.updUser = updUser;
		this.pkTaskGroup = pkTaskGroup;		
		this.taskGroupName = taskGroupName;
		this.pkTask = pkTask;
		this.task = task;
		this.isCompleted = isCompleted;
		this.dueDate = dueDate;
		this.comments = comments;
	}
	
}
