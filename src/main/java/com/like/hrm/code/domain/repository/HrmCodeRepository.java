package com.like.hrm.code.domain.repository;

import java.util.List;

import com.like.hrm.code.boundary.HrmRelationCodeDTO;
import com.like.hrm.code.boundary.HrmTypeDTO;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO;
import com.like.hrm.code.boundary.SaveHrmRelationCode;
import com.like.hrm.code.domain.model.HrmRelationCode;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;

public interface HrmCodeRepository {

	/**
	 * 인사 유형을 조회한다.
	 * @return
	 */
	List<HrmType> getHrmTypeList(HrmTypeDTO.SearchHrmType condition);
	
	boolean isHrmType(String id);
	
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
			
	/**
	 * 인사 유형 상세 코드 명단을 조회한다.
	 * @param condition
	 * @return 
	 */
	List<HrmTypeDetailCode> getTypeDetailCodeList(HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode condition);
	
	boolean isTypeDetailCode(String id);
	
	/**
	 * 인사 유형 상세 코드를 조회한다.
	 * @param id
	 * @return
	 */
	HrmTypeDetailCode getTypeDetailCode(String id);
		
	/**
	 * 인사 유형 상세 코드를 저장한다.
	 * @param typeDetailCode
	 */
	void saveTypeDetailCode(HrmTypeDetailCode typeDetailCode);
	
	/**
	 * 인사 유형 상세 코드를 삭제한다.
	 * @param typeDetailCode
	 */
	void deleteTypeDetailCode(HrmTypeDetailCode typeDetailCode);
	
	/**
	 * 연관코드리스트를 조회한다.
	 * @param condition
	 * @return
	 */
	List<SaveHrmRelationCode> getRelationCodeList(HrmRelationCodeDTO.SearchHrmRelationCode condition);
	
	boolean isRelationCode(Long id);
	
	/**
	 * 연관코드를 조회한다.
	 * @param id
	 * @return
	 */
	HrmRelationCode getRelationCode(Long id);
	
	/**
	 * 연관코드를 저장한다.
	 * @param entity
	 */
	void saveRelationCode(HrmRelationCode entity);
	
	/**
	 * 연관코드를 삭제한다.
	 * @param entity
	 */
	void deleteRelationCode(HrmRelationCode entity);	
}
