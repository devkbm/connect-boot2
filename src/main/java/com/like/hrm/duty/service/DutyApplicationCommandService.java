package com.like.hrm.duty.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.repository.DutyApplicationRepository;

@Service
@Transactional
public class DutyApplicationCommandService {

	private DutyApplicationRepository dutyApplicationRepository;
	
	public DutyApplicationCommandService(DutyApplicationRepository dutyApplicationRepository) {
		this.dutyApplicationRepository = dutyApplicationRepository;
	}
	
	public DutyApplication getDutyApplication(String dutyId) {
		return dutyApplicationRepository.getDutyApplication(dutyId);
	}
	
	public void saveDutyApplication(DutyApplication entity) {
		dutyApplicationRepository.saveDutyApplication(entity);
	}
	
	public void deleteDutyApplication(String dutyId) {
		DutyApplication entity = dutyApplicationRepository.getDutyApplication(dutyId);
		dutyApplicationRepository.deleteDutyApplication(entity);		
	}
	
}
