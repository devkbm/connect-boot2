package com.like.hrm.duty.domain.repository;

import java.util.List;

import com.like.hrm.duty.boundary.DutyApplicationDTO;
import com.like.hrm.duty.domain.model.DutyApplication;

public interface DutyApplicationRepository {

	boolean isDutyApplication(Long dutyId);
	
	DutyApplication getDutyApplication(Long dutyId);
	
	void saveDutyApplication(DutyApplication entity);
	
	void deleteDutyApplication(DutyApplication entity);
	
	List<DutyApplication> getDutyApplicationList(DutyApplicationDTO.SearchDutyApplication condition);
}
