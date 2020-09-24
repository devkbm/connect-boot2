package com.like.hrm.duty.domain.repository;

import java.util.List;

import com.like.hrm.duty.domain.model.DutyApplicationInputLimitRule;

public interface DutyApplicationInputLimitRuleRepository {

	List<DutyApplicationInputLimitRule> getDutyApplicationInputLimitRule();
	
	DutyApplicationInputLimitRule getDutyApplicationInputLimitRule(Long id);
	
	void saveDutyApplicationInputLimitRule(DutyApplicationInputLimitRule entity);
	
	void deleteDutyApplicationInputLimitRule(DutyApplicationInputLimitRule entity);
}
