package com.like.hrm.duty.infra;

import org.springframework.stereotype.Repository;

import com.like.hrm.duty.domain.model.DutyCode;
import com.like.hrm.duty.domain.repository.DutyCodeRepository;
import com.like.hrm.duty.infra.spingdata.JpaDutyCode;

@Repository
public class DutyCodeJpaRepository implements DutyCodeRepository {

	private JpaDutyCode jpaDutyCode;
	
	public DutyCodeJpaRepository(JpaDutyCode jpaDutyCode) {
		this.jpaDutyCode = jpaDutyCode;
	}
	
	@Override
	public DutyCode getDutyCode(String dutyCode) {		
		return this.jpaDutyCode.findById(dutyCode).orElse(null);
	}

	@Override
	public void saveDutyCode(DutyCode entity) {
		this.jpaDutyCode.save(entity);		
	}

	@Override
	public void deleteDutyCode(DutyCode entity) {
		this.jpaDutyCode.delete(entity);		
	}

}
