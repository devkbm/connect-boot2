package com.like.workschedule.domain.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.common.domain.AuditEntity;
import com.like.user.domain.model.User;
import com.like.workschedule.domain.model.id.WorkGroupMemberId;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "GRWWORKGROUPUSER")
public class WorkGroupMember extends AuditEntity implements Serializable {

	@EmbeddedId
	WorkGroupMemberId id;
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "WORKGROUP_ID", nullable=false, updatable=false)
	WorkGroup workGroup;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="USER_ID", insertable = false, updatable = false)
	private User user;
	
	public WorkGroupMember(WorkGroup workGroup, User user) {
		this.id = new WorkGroupMemberId(workGroup.getId(), user.getUserId());
	}
}
