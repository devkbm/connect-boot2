package com.like.todo.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "grtaskgroup")
@EntityListeners(AuditingEntityListener.class)
public class TaskGroup extends AuditEntity {		

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_task_group")
	Long pkTaskGroup;	
	
	@Column(name="task_group_name")
	String taskGroupName;		
	
	@OneToMany(mappedBy = "taskGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	List<Task> taskList;	
	
	public TaskGroup(String taskGroupName) {
		this.taskGroupName = taskGroupName;
	}
	
	public void modify(String taskGroupName) {
		this.taskGroupName = taskGroupName;		
	}
	
	public Task getTask(Long id) {
		return this.taskList.stream().filter(e -> e.pkTask.equals(id)).findFirst().orElse(null);
	}
	
	public void addTask(Task task) {
		if (this.taskList == null)
			this.taskList = new ArrayList<>();
		
		this.taskList.add(task);
	}
	
	public void removeTask(Long id) {
		this.taskList.removeIf(e -> e.pkTask.equals(id));		
	}
	
}
