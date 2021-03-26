package com.like.hrm.duty.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.duty.boundary.DutyApplicationDTO.SearchDutyApplication;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.repository.DutyApplicationQueryRepository;

@Service
@Transactional(readOnly = true)
public class DutyApplicationQueryService {

	private DutyApplicationQueryRepository repository;
	
	public DutyApplicationQueryService(DutyApplicationQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<DutyApplication> getDutyApplicationList(SearchDutyApplication condition) {
		return this.repository.getDutyApplicationList(condition);
	}
}
