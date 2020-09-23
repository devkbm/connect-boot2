package com.like.hrm.duty.domain.repository;

import com.like.hrm.duty.domain.model.DutyApplicationInputLimitRule;

public interface DutyApplicationInputLimitRuleRepository {

	DutyApplicationInputLimitRule getDutyApplicationInputLimitRule(Long id);
	
	void saveDutyApplicationInputLimitRule(DutyApplicationInputLimitRule entity);
	
	void deleteDutyApplicationInputLimitRule(DutyApplicationInputLimitRule entity);
}
