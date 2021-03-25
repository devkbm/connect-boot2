package com.like.dept.infra.jparepository;

import java.util.List;

import com.like.dept.boundary.DeptDTO.DeptHierarchy;
import com.like.dept.boundary.DeptDTO.SearchDept;
import com.like.dept.domain.model.Dept;
import com.like.dept.domain.model.QDept;
import com.like.dept.domain.repository.DeptQueryRepository;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class DeptJpaQueryRepository implements DeptQueryRepository {

	private JPAQueryFactory  queryFactory;
	private static final QDept qDept = QDept.dept;		
	
	public DeptJpaQueryRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<Dept> getDeptList(SearchDept searchCondition) {
		return queryFactory				
				.selectFrom(qDept)
				.where(searchCondition.getCondition())
				.fetch();
	}

	@Override
	public List<DeptHierarchy> getDeptHierarchy() {
		List<DeptHierarchy> rootNodeList = this.getDeptRootNodeList();
		
		List<DeptHierarchy> result = this.addDeptChildNodeList(rootNodeList);
		
		return result;
	}
	
	private List<DeptHierarchy> addDeptChildNodeList(List<DeptHierarchy> list) {
		List<DeptHierarchy> children = null;
		
		for ( DeptHierarchy node : list) {
			
			children = getDeptChildNodeList(node.getDeptCode());
			
			if (children.isEmpty()) {
				node.setLeaf(true);
				continue;
			} else {
				node.setChildren(children);
				node.setLeaf(false);
				
				// 재귀 호출
				this.addDeptChildNodeList(children);
			}			
		}
		
		return list;
	}

	private List<DeptHierarchy> getDeptRootNodeList() {
		return queryFactory
				.select(this.getDeptHierarchyConstructor())
				.from(qDept)
				.where(qDept.isRootNode())
				.orderBy(qDept.seq.asc())				
				.fetch();
	}
	
	private List<DeptHierarchy> getDeptChildNodeList(String parentDeptCode) {
		return queryFactory
				.select(this.getDeptHierarchyConstructor())
				.from(qDept)
				.where(qDept.parentDept.deptCode.eq(parentDeptCode))
				.orderBy(qDept.seq.asc())
				.fetch();
	}
	
	private ConstructorExpression<DeptHierarchy> getDeptHierarchyConstructor() {
		return Projections.constructor(
				DeptHierarchy.class,
				qDept._super.createdDt, 
				qDept._super.createdBy,
				qDept._super.modifiedDt,
				qDept._super.modifiedBy,
				qDept.parentDept.deptCode,
				qDept.deptCode,
				qDept.deptNameKorean,
				qDept.deptAbbreviationKorean,
				qDept.deptNameEnglish,
				qDept.deptAbbreviationEnglish,
				qDept.period,
				qDept.seq,
				qDept.comment);
	}

}
