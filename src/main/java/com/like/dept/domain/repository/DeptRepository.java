package com.like.dept.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.dept.domain.model.Dept;
import com.like.dept.dto.DeptDTO;


@Repository
public interface DeptRepository {
	
	Dept getDept(String deptCode);
	
	List<Dept> getAllDeptList();
	
	List<Dept> getDeptList(DeptDTO.SearchCondition searchCondition);
			
	void saveDept(Dept dept);
	
	void deleteDept(String deptCode);			
}