package com.like.hrm.duty.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.like.hrm.duty.boundary.DutyCodeDTO.SearchDutyCode;
import com.like.hrm.duty.domain.model.DutyCode;
import com.like.hrm.duty.domain.model.QDutyCode;
import com.like.hrm.duty.domain.repository.DutyCodeRepository;
import com.like.hrm.duty.infra.spingdata.JpaDutyCode;

@Repository
public class DutyCodeJpaRepository implements DutyCodeRepository {

	private JpaDutyCode jpaDutyCode;
	
	public DutyCodeJpaRepository(JpaDutyCode jpaDutyCode) {
		this.jpaDutyCode = jpaDutyCode;
	}
	
	@Override
	public boolean isDutyCode(String dutyCode) {
		return jpaDutyCode.existsById(dutyCode);
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

	@Override
	public List<DutyCode> getDutyCodeList(SearchDutyCode condition) {		
		return Lists.newArrayList(jpaDutyCode.findAll(condition.getBooleanBuilder()));
	}

	@Override
	public List<DutyCode> getDutyCodeList(Long dutyApplicationInputLimitId) {
		QDutyCode qDutyCode = QDutyCode.dutyCode1;
		
		return null;
	}

}
