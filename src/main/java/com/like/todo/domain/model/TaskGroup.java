package com.like.todo.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@ToString(callSuper=true, includeFieldNames=true)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"taskList"})
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "grtaskgroup")
@EntityListeners(AuditingEntityListener.class)
public class TaskGroup extends AuditEntity implements Serializable {	
	
	private static final long serialVersionUID = 7486045149831399610L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pk_task_group")
	Long pkTaskGroup;	
	
	@Column(name="task_group_name")
	String taskGroupName;		
	
	@OneToMany(mappedBy = "taskGroup")
	List<Task> taskList;	
	
	public TaskGroup(String taskGroupName) {
		this.taskGroupName = taskGroupName;
	}
}
