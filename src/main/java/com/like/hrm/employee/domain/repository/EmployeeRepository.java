package com.like.hrm.employee.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.employee.boundary.EmployeeDTO.SearchEmployee;
import com.like.hrm.employee.domain.model.Employee;

@Repository
public interface EmployeeRepository {

	
	/**
	 * <p>Employee 엔티티를 조회한다.</p>
	 * @param id	사원번호
	 * @return Employee 엔티티
	 */
	Employee getEmployee(String id);
		
	/**
	 * <p>조회조건에 해당하는 마지막 생성된 사원을 조회한다.</p>
	 * @param yyyy	년도
	 * @return Employee 엔티티
	 */
	Employee getLastEmployee(String yyyy);
	
	List<Employee> getEmployeeList(SearchEmployee dto);
	
	/**
	 * <p>Employee 엔티티를 저장한다.</p>
	 * @param employee
	 * @return Employee 엔티티
	 */
	Employee saveEmployee(Employee employee);
	
	/**
	 * <p>Employee 엔티티를 삭제한다.</p>
	 * @param id
	 */
	void deleteEmployee(String id);
			
}
