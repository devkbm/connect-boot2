package com.like.hrm.duty.infra;

import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.repository.DutyApplicationRepository;
import com.like.hrm.duty.infra.spingdata.JpaDutyApplication;

public class DutyApplicationJpaRepository implements DutyApplicationRepository {

	private JpaDutyApplication jpaDutyApplication;
	
	public DutyApplicationJpaRepository(JpaDutyApplication jpaDutyApplication) {
		this.jpaDutyApplication = jpaDutyApplication;
	}

	@Override
	public boolean isDutyApplication(String dutyId) {
		return jpaDutyApplication.existsById(dutyId);
	}

	@Override
	public DutyApplication getDutyApplication(String dutyId) {
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
}
