package com.like.hrm.duty.domain.service;

import java.util.List;

import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.model.DutyCode;
import com.like.hrm.duty.domain.model.DutyCodeRule;
import com.like.hrm.duty.domain.model.DutyApplicationInputLimitRule;
import com.like.hrm.duty.domain.repository.DutyApplicationInputLimitRuleRepository;
import com.like.hrm.duty.domain.repository.DutyApplicationRepository;
import com.like.hrm.duty.domain.repository.DutyCodeRepository;

public class DutyApplicationValidatorService {

	private DutyApplicationRepository dutyApplicationRepository;
	
	private DutyCodeRepository dutyCodeRepository;
	
	private DutyApplicationInputLimitRuleRepository dutyApplicationInputLimitRuleRepository; 
	
	public DutyApplicationValidatorService(DutyApplicationRepository dutyApplicationRepository
										  ,DutyCodeRepository dutyCodeRepository
										  ,DutyApplicationInputLimitRuleRepository dutyApplicationInputLimitRuleRepository) {
		this.dutyApplicationRepository = dutyApplicationRepository;
		this.dutyCodeRepository = dutyCodeRepository;
		this.dutyApplicationInputLimitRuleRepository = dutyApplicationInputLimitRuleRepository;
	}
	
	public boolean valid(DutyApplication application) {
		boolean valid = true;
		String employeeId = application.getEmployeeId();				
		List<DutyCodeRule> ruleList = getDutyCodeRuleList(application.getDutyCode());
		DutyApplicationInputLimitRule limit = null;
		
		for (DutyCodeRule rule : ruleList) {
			limit = dutyApplicationInputLimitRuleRepository.getDutyApplicationInputLimitRule(rule.getDutyApplicationInputLimitId());
			
			List<DutyCode> dutyCodeList = getDutyCodeBySameLimitRule(rule.getDutyApplicationInputLimitId());
			
			long cnt = this.dutyApplicationRepository.getDutyApplicationCount(employeeId
																			 ,dutyCodeList
																			 ,limit.getFrom()
																			 ,limit.getTo());
			
			if (cnt > limit.getCount())
				valid = false;				
		}
			
		return valid;
	}
	
	private List<DutyCode> getDutyCodeBySameLimitRule(Long id) {
		return dutyCodeRepository.getDutyCodeList(id);
	}
	
	private List<DutyCodeRule> getDutyCodeRuleList(String dutyCode) {
		return dutyCodeRepository.getDutyCode(dutyCode).getDutyCodeRule();
	}
}
