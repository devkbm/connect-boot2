package com.like.dept.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.dept.boundary.DeptDTO.DeptHierarchy;
import com.like.dept.domain.model.Dept;
import com.like.dept.domain.model.QDept;
import com.like.dept.domain.repository.DeptRepository;
import com.like.dept.infra.jparepository.springdata.JpaDept;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeptJpaRepository implements DeptRepository {
	
	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaDept jpaDept;
	
	private static final QDept qDept = QDept.dept;
	
	@Override
	public boolean isDept(String deptCode) {
		return jpaDept.existsById(deptCode);
	}
	
	@Override
	public Dept getDept(String deptCode) {
		Optional<Dept> entity = jpaDept.findById(deptCode);
		
		//return entity.isPresent() ? entity.get() : null;
		return entity.orElse(null);
	}

	@Override
	public List<Dept> getAllDeptList() {
		return jpaDept.findAll();
	}
	
	@Override
	public List<Dept> getDeptList(Predicate searchCondition) {
		return queryFactory				
				.selectFrom(qDept)
				.where(searchCondition)
				.fetch();
	}
	

	@Override
	public List<DeptHierarchy> getDeptHierarchy() {
		List<DeptHierarchy> rootNodeList = this.getDeptRootNodeList();
		
		List<DeptHierarchy> result = this.addDeptChildNodeList(rootNodeList);
		
		return result;
	}


	@Override
	public void saveDept(Dept dept) {
		jpaDept.save(dept);		
	}

	@Override
	public void deleteDept(String deptCode) {
		jpaDept.deleteById(deptCode);		
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
				qDept.fromDate,
				qDept.toDate,
				qDept.seq,
				qDept.comment);
	}

}
