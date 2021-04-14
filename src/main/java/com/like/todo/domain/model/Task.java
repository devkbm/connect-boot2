package com.like.todo.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true, exclude = {"taskGroup"})
@Getter
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "grtask")
@EntityListeners(AuditingEntityListener.class)
public class Task extends AuditEntity {		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_task")
	Long pkTask;	

	@Column(name="task")
	String task;		
	
	@Column(name="completed")
	boolean isCompleted;
	
	@Column(name="due_dt")
    LocalDate dueDate;
	
	@Column(name="comments")
	String comments;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_task_group", nullable=false, updatable=false)
	TaskGroup taskGroup;	
	
	@Builder
	public Task(TaskGroup taskGroup, String task, LocalDate dueDate, String comments) {
		this.taskGroup = taskGroup;
		this.task = task;
		this.dueDate = dueDate;
		this.comments = comments;
	}
	
	public void modify(String task
					  ,boolean isCompleted
					  ,LocalDate dueDate
					  ,String comments) {
		this.task = task;
		this.isCompleted = isCompleted;
		this.dueDate = dueDate;
		this.comments = comments;		
	}
	
}
