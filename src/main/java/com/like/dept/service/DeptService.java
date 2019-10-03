package com.like.dept.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.dept.boundary.DeptDTO;
import com.like.dept.boundary.DeptDTO.DeptHierarchy;
import com.like.dept.domain.model.Dept;
import com.like.dept.domain.repository.DeptRepository;

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
	
	public List<Dept> getDeptList(DeptDTO.SearchDept searchCondition) {
		return deptRepository.getDeptList(searchCondition.getCondition());
	}
	
	public void createDept(Dept dept) {
		deptRepository.saveDept(dept);
	}			
	
	public void saveDept(Dept dept) {				
		deptRepository.saveDept(dept);
	}
	
	public void saveDept(DeptDTO.SaveDept dto) {
		Dept dept = deptRepository.getDept(dto.getDeptCode());
		Dept parentDept = null;
		
		if (dto.getParentDeptCode() != null) {
			parentDept = deptRepository.getDept(dto.getParentDeptCode());
		}
		
		if (dept == null) {
			dept = dto.newDept(parentDept);
		} else {
			dto.modifyDept(dept, parentDept);
		}
				
		
		deptRepository.saveDept(dept);
	}
	
	public void deleteDept(String deptCode) {
		deptRepository.deleteDept(deptCode);
	}
}
