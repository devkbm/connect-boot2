package com.like.hrm.duty.domain.repository;

import com.like.hrm.duty.domain.model.DutyCode;

public interface DutyCodeRepository {

	DutyCode getDutyCode(String dutyCode);
	
	void saveDutyCode(DutyCode entity);
	
	void deleteDutyCode(DutyCode entity);
}
