package com.like.todo.domain.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"taskGroup"})
@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "grtask")
@EntityListeners(AuditingEntityListener.class)
public class Task extends AuditEntity implements Serializable {	
	
	private static final long serialVersionUID = 7486045149831399610L;

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
	
	public Task(TaskGroup taskGroup, String task, LocalDate dueDate, String comments) {
		this.taskGroup = taskGroup;
		this.task = task;
		this.dueDate = dueDate;
		this.comments = comments;
	}
	
}
