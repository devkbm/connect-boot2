package com.like.hrm.employee.domain.service;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

@Service
public class EmployeeIdGenerator {

	//@Resource(name="employeeRepository")
	private EmployeeRepository employeeRepository;
	
	/**
	 * <p>사원번호를 생성한다.</p>
	 * [사원번호 생성규칙] <br>
	 * 생성년도 + 순번(4자리)
	 * @return 사원번호
	 */
	public String generateEmpId() {
		
		int year = LocalDate.now().getYear();
		
		Employee emp = employeeRepository.getLastEmployee(year);
		
		String id = emp.getEmployeeId();
				
		return id;
	}
}
