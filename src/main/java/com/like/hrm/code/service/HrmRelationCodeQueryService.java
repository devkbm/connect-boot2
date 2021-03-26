package com.like.hrm.code.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.code.boundary.HrmRelationCodeDTO;
import com.like.hrm.code.domain.repository.HrmRelationCodeQueryRepository;

@Service
@Transactional(readOnly = true)
public class HrmRelationCodeQueryService {

	private HrmRelationCodeQueryRepository repository;
	
	public HrmRelationCodeQueryService(HrmRelationCodeQueryRepository repository) {
		this.repository = repository;
	}
	
	public List<?> getHrmRelationCodeList(HrmRelationCodeDTO.SearchHrmRelationCode condition) {
		return repository.getRelationCodeList(condition);
	}
}
