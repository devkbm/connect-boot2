package com.like.hrm.duty.domain.service;

import java.time.LocalDate;
import java.util.List;

import com.like.hrm.duty.boundary.DutyCodeDTO;
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
		boolean valid = false;
		String employeeId = application.getEmployeeId();		
		DutyCode dutyCode = dutyCodeRepository.getDutyCode(application.getDutyCode());
		List<DutyCodeRule> ruleList = dutyCode.getDutyCodeRule();
		DutyApplicationInputLimitRule limit = null;
		
		for (DutyCodeRule rule : ruleList) {
			limit = dutyApplicationInputLimitRuleRepository.getDutyApplicationInputLimitRule(rule.getDutyApplicationInputLimitId());
			
			List<DutyCode> dutyCodeList = getDutyCodeBySameLimitRule(rule.getDutyApplicationInputLimitId());
			
			long cnt = this.dutyApplicationRepository.getDutyApplicationCount(employeeId
																			 ,dutyCodeList
																			 ,limit.getFrom()
																			 ,limit.getTo());
		}
			
		return valid;
	}
	
	private List<DutyCode> getDutyCodeBySameLimitRule(Long id) {
		return dutyCodeRepository.getDutyCodeList(id);
	}
}
