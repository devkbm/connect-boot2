package com.like.dept.domain.repository;

import java.util.List;

import com.like.dept.boundary.DeptDTO;
import com.like.dept.boundary.DeptDTO.SearchDept;
import com.like.dept.domain.model.Dept;

public interface DeptQueryRepository {

	List<Dept> getDeptList(SearchDept searchCondition);
	
	List<DeptDTO.DeptHierarchy> getDeptHierarchy();
}
