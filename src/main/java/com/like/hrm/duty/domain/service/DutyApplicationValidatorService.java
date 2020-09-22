package com.like.hrm.duty.domain.service;

import java.time.LocalDate;
import java.util.List;

import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.model.DutyCode;
import com.like.hrm.duty.domain.model.DutyCodeRule;
import com.like.hrm.duty.domain.repository.DutyApplicationRepository;
import com.like.hrm.duty.domain.repository.DutyCodeRepository;

public class DutyApplicationValidatorService {

	private DutyApplicationRepository dutyApplicationRepository;
	
	private DutyCodeRepository dutyCodeRepository;
	
	public DutyApplicationValidatorService(DutyApplicationRepository dutyApplicationRepository
										  ,DutyCodeRepository dutyCodeRepository) {
		this.dutyApplicationRepository = dutyApplicationRepository;
		this.dutyCodeRepository = dutyCodeRepository;
	}
	
	public boolean valid(DutyApplication application) {
		boolean result = false;
		String employeeId = application.getEmployeeId();		
		DutyCode dutyCode = dutyCodeRepository.getDutyCode(application.getDutyCode());
		DutyCodeRule dutyCodeRule = dutyCode.getDutyCodeRule();		
		
		List<DutyCode> sameDutyRuleList = null;
		
		if (dutyCode.getDutyCodeRule() != null) {
			sameDutyRuleList = dutyCodeRepository.getDutyCodeList(dutyCodeRule);
			
			long cnt = this.dutyApplicationRepository.getDutyApplicationCount(employeeId
																			 ,sameDutyRuleList
																			 ,dutyCodeRule.getFromDate()
																			 ,dutyCodeRule.getToDate());
		} else {
			result = true;
		}
	
		
		return result;
	}
}
