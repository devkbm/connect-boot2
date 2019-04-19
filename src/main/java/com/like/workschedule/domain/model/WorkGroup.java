package com.like.workschedule.domain.model;

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

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "GRWWORKGROUP")
@EntityListeners(AuditingEntityListener.class)
public class WorkGroup extends AuditEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Column(name="NAME")
	String name;
	
	@OneToMany(mappedBy = "workGroup")
	List<Schedule> scheduleList;
	
	@OneToMany(mappedBy = "workGroup")
	List<WorkGroupMember> memberList;
	
	public WorkGroup(String name) {
		this.name = name;
		this.scheduleList = null;
		this.memberList = null;
	}
	
}
