package com.like.dept.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.dept.domain.model.Dept;
import com.like.dept.domain.model.DeptDTOAssembler;
import com.like.dept.domain.repository.DeptRepository;
import com.like.dept.dto.DeptDTO;
import com.like.dept.dto.DeptDTO.DeptHierarchy;

@Service("deptService")
@Transactional
public class DeptService {

	@Resource(name="deptJpaRepository")
	private DeptRepository deptRepository;
	
	public Dept getDept(String deptCode) {
		return deptRepository.getDept(deptCode);
	}
	
	public List<Dept> getAllDeptList() {
		return deptRepository.getAllDeptList();
	}
	
	public List<DeptHierarchy> getDeptHierarchyList() {
		return deptRepository.getDeptHierarchy();
	}
	
	public List<Dept> getDeptList(DeptDTO.SearchCondition searchCondition) {
		return deptRepository.getDeptList(searchCondition);
	}
	
	public void createDept(Dept dept) {
		deptRepository.saveDept(dept);
	}			
	
	public void saveDept(Dept dept) {				
		deptRepository.saveDept(dept);
	}
	
	public void deleteDept(String deptCode) {
		deptRepository.deleteDept(deptCode);
	}
}
