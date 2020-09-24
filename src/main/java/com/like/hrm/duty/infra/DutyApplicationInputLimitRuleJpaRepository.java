package com.like.hrm.duty.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.duty.domain.model.DutyApplicationInputLimitRule;
import com.like.hrm.duty.domain.repository.DutyApplicationInputLimitRuleRepository;
import com.like.hrm.duty.infra.spingdata.JpaDutyApplicationInputLimitRule;

@Repository
public class DutyApplicationInputLimitRuleJpaRepository implements DutyApplicationInputLimitRuleRepository {

	private JpaDutyApplicationInputLimitRule repository;
	
	public DutyApplicationInputLimitRuleJpaRepository(JpaDutyApplicationInputLimitRule repository) {
		this.repository = repository;
	}
	
	@Override
	public List<DutyApplicationInputLimitRule> getDutyApplicationInputLimitRule() { 
		return repository.findAll();
	}
	
	@Override
	public DutyApplicationInputLimitRule getDutyApplicationInputLimitRule(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void saveDutyApplicationInputLimitRule(DutyApplicationInputLimitRule entity) {
		repository.save(entity);
	}

	@Override
	public void deleteDutyApplicationInputLimitRule(DutyApplicationInputLimitRule entity) {
		repository.delete(entity);
	}
	
}
