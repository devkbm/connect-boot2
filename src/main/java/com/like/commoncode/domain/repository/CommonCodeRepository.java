package com.like.commoncode.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.commoncode.boundary.CodeComboDTO;
import com.like.commoncode.boundary.CodeDTO;
import com.like.commoncode.boundary.CodeHierarchy;
import com.like.commoncode.domain.model.Code;
import com.querydsl.core.types.Predicate;

@Repository
public interface CommonCodeRepository {		
	
	Code getCode(String codeId);
	
	List<Code> getAllCodeList();
	
	List<Code> getCodeList(String parentCodeId);
	
	List<Code> getCodeList(Predicate predicate);
	
	List<CodeHierarchy> getCodeHierarchyList(CodeDTO.SearchCode dto);
	
	List<CodeComboDTO> getCodeListByComboBox(String codeGroup);
	
	void saveCode(Code code);
	
	void deleteCode(String codeId);	
}
