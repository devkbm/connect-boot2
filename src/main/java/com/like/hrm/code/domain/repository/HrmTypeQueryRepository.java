package com.like.hrm.code.domain.repository;

import java.util.List;

import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;

public interface HrmTypeQueryRepository {

	/**
	 * 인사 유형을 조회한다.
	 * @return
	 */
	List<HrmType> getHrmTypeList(HrmTypeDTO.SearchHrmType condition);
	
	/**
	 * 인사 유형 상세 코드 명단을 조회한다.
	 * @param condition
	 * @return 
	 */
	List<HrmTypeDetailCode> getTypeDetailCodeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode condition);
	
}
