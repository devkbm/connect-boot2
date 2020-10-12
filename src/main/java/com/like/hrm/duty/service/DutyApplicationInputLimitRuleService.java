package com.like.hrm.duty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.duty.boundary.DutyApplicationInputLimitRuleDTO;
import com.like.hrm.duty.domain.model.DutyApplicationInputLimitRule;
import com.like.hrm.duty.domain.repository.DutyApplicationInputLimitRuleRepository;

@Service
@Transactional
public class DutyApplicationInputLimitRuleService {

	private DutyApplicationInputLimitRuleRepository repository;
	
	public DutyApplicationInputLimitRuleService(DutyApplicationInputLimitRuleRepository repository) {
		this.repository = repository;		
	}
	
	public List<DutyApplicationInputLimitRule> getDutyApplicationInputLimitRule() { 
		return repository.getDutyApplicationInputLimitRule();
	}
		
	public DutyApplicationInputLimitRule getDutyApplicationInputLimitRule(Long id) {
		return repository.getDutyApplicationInputLimitRule(id);
	}
	
	public void saveDutyApplicationInputLimitRule(DutyApplicationInputLimitRule entity) {
		repository.saveDutyApplicationInputLimitRule(entity);
	}
	
	public void saveDutyApplicationInputLimitRule(DutyApplicationInputLimitRuleDTO.SaveDutyApplicationInputLimitRule dto) {
		DutyApplicationInputLimitRule entity = null;
		if (dto.getId() == null) {
			entity = dto.newEntity();
		} else {
			entity = this.getDutyApplicationInputLimitRule(dto.getId());
			dto.modifyEntity(entity);
		}
		
		saveDutyApplicationInputLimitRule(entity);
	}
	
	public void deleteDutyApplicationInputLimitRule(DutyApplicationInputLimitRule entity) {
		repository.deleteDutyApplicationInputLimitRule(entity);
	}
	
	public void deleteDutyApplicationInputLimitRule(Long id) {
		DutyApplicationInputLimitRule entity = repository.getDutyApplicationInputLimitRule(id);
		repository.deleteDutyApplicationInputLimitRule(entity);
	}
}
