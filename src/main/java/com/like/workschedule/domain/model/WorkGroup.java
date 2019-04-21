package com.like.workschedule.domain.model;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"scheduleList"})
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
	
	@OneToMany(mappedBy = "workGroup", fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	List<WorkGroupMember> memberList = new ArrayList<>();
	
	public WorkGroup(String name) {
		this.name = name;
		this.scheduleList = null;
		this.memberList = null;
	}
	
	public void addWorkGroupMember(WorkGroupMember member) {
		if (this.memberList == null) { 
			this.memberList = new ArrayList<>();
		}
		log.info( member.toString());
		log.info( String.valueOf(this.memberList.contains(member)));
		
		// 중복 방지
		if (!this.memberList.contains(member)) {
			this.memberList.add(member);
		}
		
		// 참조 추가
		member.setWorkGroup(this);
		
	}
	
}
