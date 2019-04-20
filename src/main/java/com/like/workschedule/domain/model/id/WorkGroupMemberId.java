package com.like.workschedule.domain.model.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class WorkGroupMemberId implements Serializable {
	
	@Column(name="WORKGROUP_ID")
	Long workGroupId;
		
	@Column(name="USER_ID")
	String userId;	
	
	public WorkGroupMemberId(Long teamId, String userId) {
		this.workGroupId = teamId;
		this.userId = userId;
	}
	
}
