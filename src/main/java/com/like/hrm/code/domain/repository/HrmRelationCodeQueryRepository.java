package com.like.hrm.code.domain.repository;

import java.util.List;

import com.like.hrm.code.boundary.HrmRelationCodeDTO;
import com.like.hrm.code.boundary.SaveHrmRelationCode;

public interface HrmRelationCodeQueryRepository {

	/**
	 * 연관코드리스트를 조회한다.
	 * @param condition
	 * @return
	 */
	List<SaveHrmRelationCode> getRelationCodeList(HrmRelationCodeDTO.SearchHrmRelationCode condition);
}
