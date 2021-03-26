package com.like.hrm.code.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;
import com.like.hrm.code.domain.repository.HrmTypeQueryRepository;

@Service
@Transactional(readOnly = true)
public class HrmTypeQueryService {

	private HrmTypeQueryRepository repository;	
		
	public HrmTypeQueryService(HrmTypeQueryRepository repository) {		
		this.repository = repository;
	}
				
	public List<HrmType> getHrmDeptTypeList(HrmTypeDTO.SearchHrmType condition) {
		return repository.getHrmTypeList(condition);
	}
	
	public List<HrmTypeDetailCode> getHrmDeptTypeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode condition) {
		return repository.getTypeDetailCodeList(condition);
	}	
	
	
	
}
