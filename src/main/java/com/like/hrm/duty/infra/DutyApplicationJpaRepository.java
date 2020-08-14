package com.like.hrm.duty.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.like.hrm.duty.boundary.DutyApplicationDTO.SearchDutyApplication;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.repository.DutyApplicationRepository;
import com.like.hrm.duty.infra.spingdata.JpaDutyApplication;

@Repository
public class DutyApplicationJpaRepository implements DutyApplicationRepository {

	private JpaDutyApplication jpaDutyApplication;
	
	public DutyApplicationJpaRepository(JpaDutyApplication jpaDutyApplication) {
		this.jpaDutyApplication = jpaDutyApplication;
	}

	@Override
	public boolean isDutyApplication(Long dutyId) {
		return jpaDutyApplication.existsById(dutyId);
	}

	@Override
	public DutyApplication getDutyApplication(Long dutyId) {
		return jpaDutyApplication.findById(dutyId).orElse(null);
	}

	@Override
	public void saveDutyApplication(DutyApplication entity) {
		jpaDutyApplication.save(entity);		
	}

	@Override
	public void deleteDutyApplication(DutyApplication entity) {
		jpaDutyApplication.delete(entity);
		
	}

	@Override
	public List<DutyApplication> getDutyApplicationList(SearchDutyApplication condition) {
		return Lists.newArrayList(jpaDutyApplication.findAll(condition.getBooleanBuilder()));
	}
}
