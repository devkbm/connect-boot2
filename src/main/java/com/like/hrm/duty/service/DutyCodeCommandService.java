package com.like.hrm.duty.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.duty.domain.model.DutyCode;
import com.like.hrm.duty.domain.repository.DutyCodeRepository;

@Service
@Transactional
public class DutyCodeCommandService {

	private DutyCodeRepository dutyCodeRepository;
	
	public DutyCodeCommandService(DutyCodeRepository dutyCodeRepository) {
		this.dutyCodeRepository = dutyCodeRepository;
	}
	
	public DutyCode getDutyCode(String dutyCode) {
		return this.dutyCodeRepository.getDutyCode(dutyCode);
	}
	
	public void saveDutyCode(DutyCode dutyCode) {
		this.dutyCodeRepository.saveDutyCode(dutyCode);
	}
	
	public void deleteDutyCode(String dutyCode) {
		DutyCode entity = this.dutyCodeRepository.getDutyCode(dutyCode);
		
		if (entity == null)
			throw new EntityNotFoundException(dutyCode + "근무코드가 존재하지 않습니다.");
		
		this.dutyCodeRepository.deleteDutyCode(entity);
	}
}
