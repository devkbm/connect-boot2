package com.like.workschedule.domain.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.common.domain.AuditEntity;
import com.like.user.domain.model.User;
import com.like.workschedule.domain.model.id.WorkGroupMemberId;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false)
@Entity
@IdClass(WorkGroupMemberId.class)
@Table(name = "GRWWORKGROUPUSER")
public class WorkGroupMember extends AuditEntity {
	
	@JsonBackReference
	@Id	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKGROUP_ID")
	WorkGroup workGroup;
	
	@JsonBackReference
	@Id	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	User user;
	
	public WorkGroupMember(WorkGroup workGroup, User user) {
		this.workGroup = workGroup;
		this.user = user;
	}
	
	public String getUserId() {
		return this.user.getUserId();
	}

	@Override
	public String toString() {
		return "WorkGroupMember [workGroup=" + workGroup.id + ", user=" + user.getUserId() + "]";
	}
	
	public void setWorkGroup(WorkGroup workGroup) {
		// 기존에 존재했던 참조 삭제
		if (this.workGroup != null) {
			this.workGroup.getMemberList().remove(this);
		}
		
		this.workGroup = workGroup;
		
		// 참조 추가
		if (workGroup != null && !workGroup.getMemberList().contains(this)) {
			this.workGroup.getMemberList().add(this);
		}
		
	}
	
	
}
