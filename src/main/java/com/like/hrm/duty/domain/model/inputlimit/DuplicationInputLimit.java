package com.like.hrm.duty.domain.model.inputlimit;

import java.util.List;

import com.like.hrm.duty.boundary.DutyApplicationDTO.SearchDutyApplication;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.model.InputLimitable;
import com.like.hrm.duty.domain.repository.DutyApplicationRepository;

/**
 * 기존 입력된 근태 데이터의 기간내 중복 입력을 제한 하는 기능 
 */
public class DuplicationInputLimit implements InputLimitable {

	private DutyApplicationRepository dutyApplicationRepository;
	
	public DuplicationInputLimit(DutyApplicationRepository dutyApplicationRepository) {
		this.dutyApplicationRepository = dutyApplicationRepository;
	}
	
	@Override
	public boolean isLimit(DutyApplication entity) {
		if (isDuplicated(entity))
			return true;
		
		return false;
	}
	
	private boolean isDuplicated(DutyApplication entity) {
		SearchDutyApplication search = SearchDutyApplication.builder()
														  	.employeeId(entity.getEmployeeId())
														  	.build();				
		
		List<DutyApplication> dutyList = dutyApplicationRepository.getDutyApplicationList(search);
		
		for (DutyApplication duty : dutyList) {
			
			// 중복되는 근태가 있을 경우
			if (duty.getPeriod().isBetween(entity.getPeriod()))
				return true;
		}
		
		return false;
	}
	

}
