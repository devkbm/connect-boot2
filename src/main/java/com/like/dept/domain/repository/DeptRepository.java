package com.like.dept.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.dept.boundary.DeptDTO;
import com.like.dept.domain.model.Dept;
import com.querydsl.core.types.Predicate;


@Repository
public interface DeptRepository {
	
	boolean isDept(String deptCode);
	
	Dept getDept(String deptCode);
	
	List<Dept> getAllDeptList();
	
	List<Dept> getDeptList(Predicate searchCondition);
	
	List<DeptDTO.DeptHierarchy> getDeptHierarchy();
			
	void saveDept(Dept dept);
	
	void deleteDept(String deptCode);			
}