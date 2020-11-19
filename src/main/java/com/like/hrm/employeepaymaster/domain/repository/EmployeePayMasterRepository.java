package com.like.hrm.employeepaymaster.domain.repository;

import com.like.hrm.employeepaymaster.domain.model.EmployeePayMaster;

public interface EmployeePayMasterRepository {

	EmployeePayMaster getEmployeePayMaster(Long id);
	
	void save(EmployeePayMaster entity);
	
	void delete(EmployeePayMaster entity);
}
