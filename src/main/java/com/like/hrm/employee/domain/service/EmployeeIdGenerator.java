package com.like.hrm.employee.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.repository.EmployeeQueryRepository;

@Service
public class EmployeeIdGenerator {
	
	private EmployeeQueryRepository repository;
	
	public EmployeeIdGenerator(EmployeeQueryRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * <p>사원번호를 생성한다.</p>
	 * [사원번호 생성규칙] <br>
	 * 생성년도 + 순번(4자리)
	 * @return 사원번호
	 */
	public String generateEmpId() {
				
		String currentYear = String.valueOf(LocalDate.now().getYear());
		String id = null;
		
		Employee emp = repository.getLastEmployee(currentYear);
		
		if (emp == null) {
			id = currentYear + "0001";
		} else {
			id = currentYear + String.format("%04d",Integer.parseInt(emp.getEmployeeId().substring(4, 8), 10) + 1);
		}			
				
		return id;
	}
}
