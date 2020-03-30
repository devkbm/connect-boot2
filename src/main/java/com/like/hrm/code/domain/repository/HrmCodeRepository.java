package com.like.hrm.code.domain.repository;

import java.util.List;

import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;

public interface HrmCodeRepository {

	/**
	 * 인사 유형을 조회한다.
	 * @return
	 */
	List<HrmType> getHrmTypeList(HrmTypeDTO.SearchHrmType condition);
	
	/**
	 * 인사 유형을 조회한다.
	 * @param id
	 * @return
	 */
	HrmType getHrmType(String id);
	
	/**
	 * 인사 유형을 저장한다.
	 * @param deptType
	 */
	void saveHrmType(HrmType hrmType);
	
	/**
	 * 인사 유형을 삭제한다.
	 * @param deptType
	 */
	void deleteHrmType(HrmType hrmType);
			
	List<HrmTypeDetailCode> getTypeDetailCodeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode condition);
	
	HrmTypeDetailCode getTypeDetailCode(String id);
		
	void saveTypeDetailCode(HrmTypeDetailCode typeDetailCode);
	
	void deleteTypeDetailCode(HrmTypeDetailCode typeDetailCode);
	
}
