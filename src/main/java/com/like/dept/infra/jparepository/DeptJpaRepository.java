package com.like.dept.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.dept.domain.model.Dept;
import com.like.dept.domain.model.QDept;
import com.like.dept.domain.repository.DeptRepository;
import com.like.dept.dto.DeptDTO.SearchCondition;
import com.like.dept.infra.jparepository.springdata.JpaDept;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DeptJpaRepository implements DeptRepository {
	
	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaDept jpaDept;
	
	private static final QDept qDept = QDept.dept;
	
	@Override
	public Dept getDept(String deptCode) {
		Optional<Dept> dept = jpaDept.findById(deptCode);
		
		return dept.get();
	}

	@Override
	public List<Dept> getAllDeptList() {
		return jpaDept.findAll();
	}
	
	@Override
	public List<Dept> getDeptList(SearchCondition searchCondition) {
		return queryFactory				
				.selectFrom(qDept)
				.where(searchCondition.getCondition())
				.fetch();
	}

	@Override
	public void saveDept(Dept dept) {
		jpaDept.save(dept);		
	}

	@Override
	public void deleteDept(String deptCode) {
		jpaDept.deleteById(deptCode);		
	}

	

}
