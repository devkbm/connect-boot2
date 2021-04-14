package com.like.workschedule.domain.model;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.core.domain.AuditEntity;
import com.like.user.domain.model.User;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"scheduleList", "memberList"})
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
	
	@Column(name="COLOR")
	String color;
	
	@OneToMany(mappedBy = "workGroup")
	List<Schedule> scheduleList;
	
	@OrderBy("USER_ID asc")
	@OneToMany(mappedBy = "workGroup", fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval=true)
	Set<WorkGroupMember> memberList = new LinkedHashSet<>();
	
	public WorkGroup(String name, String color) {		
		this.name = name;
		this.color = color;
		this.scheduleList = null;
		this.memberList = null;
	}
	
	public void modifyEntity(String name, String color) {
		this.name = name;
		this.color = color;
	}
	
	public void addWorkGroupMember(WorkGroupMember member) {
		if (this.memberList == null) { 
			this.memberList = new LinkedHashSet<>();
		}		
		
		// 중복 방지
		if (!this.memberList.contains(member)) {
			this.memberList.add(member);
		}
		
		// 참조 추가
		member.setWorkGroup(this);		
	}
	
	public void deleteWorkGroupMember(User user) {		
		this.memberList.remove(new WorkGroupMember(this, user));		
	}
	
	public void clearWorkGroupMember() {
		if (this.memberList == null) {
			this.memberList = new LinkedHashSet<>();			
		} else if (!this.memberList.isEmpty()) {
			this.memberList.clear();
		}
	}
	
}
