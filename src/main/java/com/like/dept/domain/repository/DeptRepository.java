package com.like.dept.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.dept.boundary.DeptDTO;
import com.like.dept.boundary.SearchCondition;
import com.like.dept.domain.model.Dept;


@Repository
public interface DeptRepository {
	
	Dept getDept(String deptCode);
	
	List<Dept> getAllDeptList();
	
	List<Dept> getDeptList(SearchCondition.DeptSearch searchCondition);
	
	List<DeptDTO.DeptHierarchy> getDeptHierarchy();
			
	void saveDept(Dept dept);
	
	void deleteDept(String deptCode);			
}