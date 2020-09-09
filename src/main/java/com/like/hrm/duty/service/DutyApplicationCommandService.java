package com.like.hrm.duty.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.duty.boundary.DutyApplicationDTO;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.repository.DutyApplicationRepository;

@Service
@Transactional
public class DutyApplicationCommandService {

	private DutyApplicationRepository dutyApplicationRepository;
	
	public DutyApplicationCommandService(DutyApplicationRepository dutyApplicationRepository) {
		this.dutyApplicationRepository = dutyApplicationRepository;
	}
	
	public DutyApplication getDutyApplication(Long dutyId) {
		return dutyApplicationRepository.getDutyApplication(dutyId);
	}
	
	public void saveDutyApplication(DutyApplicationDTO.SaveDutyApplication dto) {
		DutyApplication entity = null;
		
		if (dto.getDutyId() == null) {
			entity = dto.newEntity();
		} else {
			entity = dutyApplicationRepository.getDutyApplication(dto.getDutyId());
			dto.modifyEntity(entity);
		}
		
		dutyApplicationRepository.saveDutyApplication(entity);
	}
	
	public void deleteDutyApplication(Long dutyId) {
		DutyApplication entity = dutyApplicationRepository.getDutyApplication(dutyId);
		dutyApplicationRepository.deleteDutyApplication(entity);		
	}
	
}
