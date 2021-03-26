package com.like.commoncode.domain.repository;

import java.util.List;

import com.like.commoncode.boundary.CodeComboDTO;
import com.like.commoncode.boundary.CodeDTO;
import com.like.commoncode.boundary.CodeHierarchy;
import com.like.commoncode.domain.model.Code;
import com.querydsl.core.types.Predicate;

public interface CommonCodeQueryRepository {

	List<Code> getCodeList(String parentCodeId);
	
	List<Code> getCodeList(Predicate predicate);
	
	List<CodeHierarchy> getCodeHierarchyList(CodeDTO.SearchCode dto);
	
	List<CodeComboDTO> getCodeListByComboBox(String codeGroup);
}
