package com.like.hrm.duty.domain.repository;

import java.util.List;

import com.like.hrm.duty.boundary.DutyCodeDTO;
import com.like.hrm.duty.domain.model.DutyCode;
import com.like.hrm.duty.domain.model.DutyCodeLimitRule;

public interface DutyCodeRepository {

	boolean isDutyCode(String dutyCode);
	
	DutyCode getDutyCode(String dutyCode);
	
	void saveDutyCode(DutyCode entity);
	
	void deleteDutyCode(DutyCode entity);
	
	List<DutyCode> getDutyCodeList(DutyCodeDTO.SearchDutyCode condition);	
	
	DutyCodeLimitRule getDutyCodeRule(Long id);
	
	void ssaveDutyCodeRule(DutyCodeLimitRule entity);
	
	void deleteDutyCodeRule(DutyCodeLimitRule entity);
	
	List<DutyCode> getDutyCodeList(DutyCodeLimitRule dutyCodeRule);
}
