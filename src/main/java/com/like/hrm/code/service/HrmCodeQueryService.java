package com.like.hrm.code.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.code.boundary.HrmRelationCodeDTO;
import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;
import com.like.hrm.code.domain.repository.HrmCodeRepository;

@Service
@Transactional(readOnly = true)
public class HrmCodeQueryService {

	private HrmCodeRepository hrmCodeRepository;
		
	public HrmCodeQueryService(HrmCodeRepository hrmCodeRepository) {		
		this.hrmCodeRepository = hrmCodeRepository;
	}
				
	public List<HrmType> getHrmDeptTypeList(HrmTypeDTO.SearchHrmType condition) {
		return hrmCodeRepository.getHrmTypeList(condition);
	}
	
	public List<HrmTypeDetailCode> getHrmDeptTypeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode condition) {
		return hrmCodeRepository.getTypeDetailCodeList(condition);
	}	
	
	public List<?> getHrmRelationCodeList(HrmRelationCodeDTO.SearchHrmRelationCode condition) {
		return hrmCodeRepository.getRelationCodeList(condition);
	}
	
}
