package com.like.hrm.duty.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.duty.boundary.DutyCodeDTO;
import com.like.hrm.duty.domain.model.DutyCode;
import com.like.hrm.duty.domain.repository.DutyCodeRepository;

@Service
@Transactional
public class DutyCodeCommandService {

	private DutyCodeRepository repository;
			
	public DutyCodeCommandService(DutyCodeRepository repository) {
		this.repository = repository;		
	}
	
	public DutyCode getDutyCode(String dutyCode) {
		return this.repository.findById(dutyCode).orElse(null);
	}
	
	public void saveDutyCode(DutyCode dutyCode) {
		this.repository.save(dutyCode);
	}
	
	public void saveDutyCode(DutyCodeDTO.SaveDutyCode dto) {
		DutyCode entity = repository.findById(dto.getDutyCode()).orElse(null);
		
		if (entity == null) {
			entity = dto.newEntity();
		} else {			
			dto.modifyEntity(entity);
		}
				
		this.repository.save(entity);		
	}	
	
	public void deleteDutyCode(String dutyCode) {		
		this.repository.deleteById(dutyCode);
	}
		
	
}
