package com.like.hrm.employee.domain.repository;

import java.util.List;

import com.like.hrm.employee.boundary.EmployeeDTO.SearchEmployee;
import com.like.hrm.employee.domain.model.Employee;

public interface EmployeeQueryRepository {

	List<Employee> getEmployeeList(SearchEmployee dto);
	
	/**
	 * <p>조회조건에 해당하는 마지막 생성된 사원을 조회한다.</p>
	 * @param yyyy	년도
	 * @return Employee 엔티티
	 */
	Employee getLastEmployee(String yyyy);
}
