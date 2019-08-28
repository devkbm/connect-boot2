package com.like.hrm.employee.domain.service;

import java.time.LocalDate;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

@Service
public class EmployeeIdGenerator {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * <p>사원번호를 생성한다.</p>
	 * [사원번호 생성규칙] <br>
	 * 생성년도 + 순번(4자리)
	 * @return 사원번호
	 */
	public String generateEmpId() {
				
		String currentYear = String.valueOf(LocalDate.now().getYear());
		String id = null;
		
		Employee emp = employeeRepository.getLastEmployee(currentYear);
		
		if (emp == null) {
			id = currentYear + "0001";
		} else {
			id = currentYear + String.format("%04d",Integer.parseInt(emp.getEmployeeId().substring(4, 8), 10) + 1);
		}			
				
		return id;
	}
}
