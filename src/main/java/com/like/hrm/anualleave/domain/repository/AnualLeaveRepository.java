package com.like.hrm.anualleave.domain.repository;

import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;

public interface AnualLeaveRepository {

	AnualLeave getAnualLeave(AnualLeaveId id);
	
	void saveAnualLeave(AnualLeave entity);
	
	void deleteAnualLeave(AnualLeave entity);	
}
