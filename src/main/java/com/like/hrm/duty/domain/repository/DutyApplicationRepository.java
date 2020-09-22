package com.like.hrm.duty.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.like.hrm.duty.boundary.DutyApplicationDTO;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.model.DutyCode;

public interface DutyApplicationRepository {

	boolean isDutyApplication(Long dutyId);
	
	DutyApplication getDutyApplication(Long dutyId);
	
	void saveDutyApplication(DutyApplication entity);
	
	void deleteDutyApplication(DutyApplication entity);
	
	List<DutyApplication> getDutyApplicationList(DutyApplicationDTO.SearchDutyApplication condition);
	
	long getDutyApplicationCount(String employeeId, List<DutyCode> dutyCodeList, LocalDate fromDate, LocalDate toDate);
}
