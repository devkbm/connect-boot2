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
		return repository.findAll();
	}
		
	public DutyApplicationInputLimitRule getDutyApplicationInputLimitRule(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveDutyApplicationInputLimitRule(DutyApplicationInputLimitRule entity) {
		repository.save(entity);
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
		repository.delete(entity);
	}
	
	public void deleteDutyApplicationInputLimitRule(Long id) {
		repository.deleteById(id);
	}
}
