package com.like.hrm.duty.domain.repository;

import com.like.hrm.duty.domain.model.DutyApplication;

public interface DutyApplicationRepository {

	boolean isDutyApplication(String dutyId);
	
	DutyApplication getDutyApplication(String dutyId);
	
	void saveDutyApplication(DutyApplication entity);
	
	void deleteDutyApplication(DutyApplication entity);
}
