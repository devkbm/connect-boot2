package com.like.commoncode.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.like.commoncode.boundary.CodeComboDTO;
import com.like.commoncode.boundary.CodeDTO.SearchCode;
import com.like.commoncode.boundary.CodeHierarchy;
import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.QCode;
import com.like.commoncode.domain.repository.CommonCodeQueryRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CommonCodeJpaQueryRepository implements CommonCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	private final QCode qCode = QCode.code1;
			
	public CommonCodeJpaQueryRepository(JPAQueryFactory	queryFactory) {
		this.queryFactory = queryFactory;
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
	public List<CodeHierarchy> getCodeHierarchyList(SearchCode dto) {
		List<CodeHierarchy> rootNodeList = this.getCodeRootNodeList(dto.getSystemTypeCode());
		
		List<CodeHierarchy> result = this.addCodeChildrenList(rootNodeList);
		
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

	private List<CodeHierarchy> getCodeRootNodeList(String systemTypeCode) {
		
		BooleanBuilder builder = new BooleanBuilder();
		
		builder.and(qCode.isRootNode())
		       .and(qCode.enabled());
		
		if (!StringUtils.isEmpty(systemTypeCode)) {
			builder.and(qCode.systemTypeCode.eq(systemTypeCode));
		}		
				
		return queryFactory
				.select(this.getCodehierarchyConstructor())
				.from(qCode)
				.where(builder)			
				.orderBy(qCode.seq.asc())
				.fetch();
	}
		
	private List<CodeHierarchy> getCodeChildNodeList(String parentId) {
		BooleanBuilder builder = new BooleanBuilder();
		
		builder
			.and(qCode.parentCode.id.eq(parentId))
			.and(qCode.enabled());
			
		return queryFactory
				.select(this.getCodehierarchyConstructor())
				.from(qCode)
				.where(builder)
				.orderBy(qCode.seq.asc())
				.fetch();
	}
	
	/**
	 * 하위 노드를 검색후 하위 노드가 존재하면 상위 노드에 하위 노드 추가함(재귀 함수)
	 * @param list
	 * @return
	 */
	private List<CodeHierarchy> addCodeChildrenList(List<CodeHierarchy> list) {
		List<CodeHierarchy> children = null;
		
		for ( CodeHierarchy code : list ) {
			
			children = getCodeChildNodeList(code.getId());
			
			if (children.isEmpty()) {
				code.setLeaf(true);
				continue;
			} else {
				code.setChildren(children);
				code.setLeaf(false);
				
				// 재귀 호출
				addCodeChildrenList(children);
			}				
		}
		
		return list;
	}
	
	
	private ConstructorExpression<CodeHierarchy> getCodehierarchyConstructor() {		
		return Projections.constructor(
				CodeHierarchy.class,
				qCode._super.createdDt, 
				qCode._super.createdBy, 
				qCode._super.modifiedDt, 
				qCode._super.modifiedBy,
				qCode.id, 
				qCode.systemTypeCode,
				qCode.parentCode.id, 
				qCode.code, 
				qCode.codeName, 
				qCode.codeNameAbbreviation,
				qCode.fromDate, 
				qCode.toDate, 
				qCode.seq, 
				qCode.cmt);		
	}

}
