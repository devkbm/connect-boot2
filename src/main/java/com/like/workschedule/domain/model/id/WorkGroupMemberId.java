package com.like.workschedule.domain.model.id;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@EqualsAndHashCode
@NoArgsConstructor
public class WorkGroupMemberId implements Serializable {
		
	private static final long serialVersionUID = -9015996959356053573L;

	Long workGroup;
			
	String user;		

	public WorkGroupMemberId(Long workgroupId, String userId) {		
		this.workGroup = workgroupId;
		this.user = userId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkGroupMemberId other = (WorkGroupMemberId) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (workGroup == null) {
			if (other.workGroup != null)
				return false;
		} else if (!workGroup.equals(other.workGroup))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((workGroup == null) ? 0 : workGroup.hashCode());
		return result;
	}
	
	
}
