package com.like.commoncode.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.dto.CodeComboDTO;
import com.like.commoncode.dto.CodeDTO;
import com.like.commoncode.dto.CodeDTO.CodeHierarchy;
import com.like.commoncode.infra.jparepository.CodeJpaRepository;
import com.querydsl.core.types.Predicate;

@Service
@Transactional(readOnly=true)
public class CommonCodeQueryService {

	@Resource(name="codeJpaRepository")
	private CodeJpaRepository codeJpaRepository;
			
	public Code getCode(String commonCodeId) {
		return codeJpaRepository.getCode(commonCodeId);
	}
	
	public List<Code> getAllCodeList() {		
		return codeJpaRepository.getAllCodeList();
	}
	
	public List<Code> getCodeList(String parentCodeId) {		
		return codeJpaRepository.getCodeList(parentCodeId);
	}
	
	public List<Code> getCodeList(CodeDTO.SearchCondition searchCondition) {		
		return codeJpaRepository.getCodeList(searchCondition.getCondition());
	}
	
	public List<CodeHierarchy> getCodeHierarchyList(CodeDTO.SearchCondition searchCondition) {		
		
		return codeJpaRepository.getCodeHierarchyList(searchCondition.getCondition());
	}
	
	public List<CodeComboDTO> getCodeListByComboBox(String codeGroup) {
		return codeJpaRepository.getCodeListByComboBox(codeGroup);
	}	
}
