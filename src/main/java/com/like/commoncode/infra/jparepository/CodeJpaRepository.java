package com.like.commoncode.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.QCode;
import com.like.commoncode.domain.repository.CommonCodeRepository;
import com.like.commoncode.dto.CodeComboDTO;
import com.like.commoncode.dto.CodeDTO.CodeHierarchy;
import com.like.commoncode.infra.jparepository.springdata.JpaCommonCode;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CodeJpaRepository implements CommonCodeRepository {
				
	@Autowired
	private JPAQueryFactory	queryFactory;	
	
	@Autowired
	private JpaCommonCode jpaCommonCode;
		
	private final QCode qCode = QCode.code1;
		

	@Override
	public Code getCode(String codeId) {
		Optional<Code> code = jpaCommonCode.findById(codeId);
		
		return code.get();
	}
	
	@Override
	public List<Code> getAllCodeList() {
		return queryFactory
				.selectFrom(qCode)				
				.fetch();
	}

	@Override
	public List<Code> getCodeList(String parentCodeId) {		
		return queryFactory
				.selectFrom(qCode)
				.where(qCode.parentCode.id.eq(parentCodeId))
				.fetch();
	}
		
	@Override
	public List<Code> getCodeList(Predicate predicate) {
		return queryFactory
				.selectFrom(qCode)
				.where(predicate)
				.fetch();
	}
	
	@Override
	public List<CodeHierarchy> getCodeHierarchyList(Predicate predicate) {
		List<CodeHierarchy> rootNodeList = this.getRootCodeList();
		
		List<CodeHierarchy> result = this.addChildrenCodeList(rootNodeList);
		
		return result;
	}
	
	
	@Override
	public List<CodeComboDTO> getCodeListByComboBox(String codeGroup) {
		
		return null; /* queryFactory
				.select(Projections.constructor(CodeComboDTO.class, qCommonCode.id.code,qCommonCode.codeName,qCommonCode.codeNameAbbreviation))
				.from(qCommonCode)
				.where(qCommonCode.id.codeGroup.eq(codeGroup))
				.fetch();*/
	}

	@Override
	public void saveCode(Code code) {						
		jpaCommonCode.save(code);		
	}

	@Override
	public void deleteCode(String codeId) {
		jpaCommonCode.deleteById(codeId);		
	}	
	
	private List<CodeHierarchy> getRootCodeList() {
		
		return queryFactory
				.select(Projections.constructor(CodeHierarchy.class,
						qCode._super.createdDt, qCode._super.createdBy, qCode._super.modifiedDt, qCode._super.modifiedBy,
						qCode.id, qCode.parentCode.id, qCode.code, qCode.codeName, qCode.codeNameAbbreviation,
						qCode.fromDate, qCode.toDate, qCode.seq, qCode.cmt))
				.from(qCode)
				.where(qCode.isRootNode()
						.and(qCode.enabled()))					
				.fetch();
	}
		
	private List<CodeHierarchy> getChildrenCodeList(String parentId) {
		return queryFactory
				.select(Projections.constructor(CodeHierarchy.class,
						qCode._super.createdDt, qCode._super.createdBy, qCode._super.modifiedDt, qCode._super.modifiedBy,
						qCode.id, qCode.parentCode.id, qCode.code, qCode.codeName, qCode.codeNameAbbreviation,
						qCode.fromDate, qCode.toDate, qCode.seq, qCode.cmt))
				.from(qCode)
				.where(qCode.parentCode.id.eq(parentId)
					.and(qCode.enabled()))
				.fetch();
	}
	
	/**
	 * 하위 노드를 검색후 하위 노드가 존재하면 상위 노드에 하위 노드 추가함(재귀 함수)
	 * @param list
	 * @return
	 */
	private List<CodeHierarchy> addChildrenCodeList(List<CodeHierarchy> list) {
		List<CodeHierarchy> children = null;
		
		for ( CodeHierarchy code : list ) {
			
			children = getChildrenCodeList(code.getId());
			
			if (children.isEmpty()) {
				code.setLeaf(true);
				continue;
			} else {
				code.setChildren(children);
				code.setLeaf(false);
				
				// 재귀 호출
				addChildrenCodeList(children);
			}				
		}
		
		return list;
	}

	

	
}