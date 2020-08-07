package com.like.hrm.duty.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.duty.boundary.DutyCodeDTO;
import com.like.hrm.duty.domain.model.DutyCode;
import com.like.hrm.duty.domain.repository.DutyCodeRepository;

@Service
@Transactional(readOnly = true)
public class DutyCodeQueryService {

	private DutyCodeRepository dutyCodeRepository;
	
	public DutyCodeQueryService(DutyCodeRepository dutyCodeRepository) {
		this.dutyCodeRepository = dutyCodeRepository;
	}
	
	public List<DutyCode> getDutyCodeList(DutyCodeDTO.SearchDutyCode condition) {
		return this.dutyCodeRepository.getDutyCodeList(condition);
	}
}
