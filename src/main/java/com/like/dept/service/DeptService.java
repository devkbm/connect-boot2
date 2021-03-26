package com.like.dept.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.dept.boundary.DeptDTO;
import com.like.dept.domain.model.Dept;
import com.like.dept.domain.repository.DeptRepository;

@Service("deptService")
@Transactional
public class DeptService {

	private DeptRepository deptRepository;
	
	public DeptService(DeptRepository deptRepository) {
		this.deptRepository = deptRepository;
	}

	public boolean isDept(String deptCode) {
		return deptRepository.existsById(deptCode);
	}
	
	public Dept getDept(String deptCode) {
		return deptRepository.findById(deptCode).orElse(null);
	}	
	
	public void createDept(Dept dept) {
		deptRepository.save(dept);
	}			
	
	public void saveDept(Dept dept) {				
		deptRepository.save(dept);
	}
	
	public void saveDept(DeptDTO.SaveDept dto) {
		Dept dept = deptRepository.findById(dto.getDeptCode()).orElse(null);
		Dept parentDept = dto.getParentDeptCode() == null ? null : deptRepository.findById(dto.getParentDeptCode()).orElse(null); 			
		
		if (dept == null) {
			dept = dto.newDept(parentDept);
		} else {
			dto.modifyDept(dept, parentDept);
		}				
		
		deptRepository.save(dept);
	}
	
	public void deleteDept(String deptCode) {
		deptRepository.deleteById(deptCode);
	}
}
