package com.like.hrm.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.common.vo.DatePeriod;
import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.JobChangeHistory;
import com.like.hrm.employee.domain.model.StatusChangeHistory;
import com.like.hrm.employee.domain.repository.EmployeeRepository;
import com.like.hrm.employee.domain.service.EmployeeIdGenerator;

@Service("employeeService")
@Transactional
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	private EmployeeIdGenerator idGenerator;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository, EmployeeIdGenerator idGenerator) {
		this.employeeRepository = employeeRepository;
		this.idGenerator = idGenerator;
	}
	
	public List<Employee> getEmployeeList(EmployeeDTO.SearchEmployee dto) {
		return employeeRepository.getEmployeeList(dto);
	}
	
	public Employee getEmployee(String id) {
		return employeeRepository.getEmployee(id);										
	}
	
	public void saveEmployee(Employee employee) {				
		employeeRepository.saveEmployee(employee);
	}
	
	public void saveEmployee(EmployeeDTO.SaveEmployee dto) {
		Employee employee = employeeRepository.getEmployee(dto.getId());
		
		dto.modifyEntity(employee);
		
		employeeRepository.saveEmployee(employee);
	}
	
	public void newEmployee(EmployeeDTO.NewEmployee dto) {										
		
		Employee emp = new Employee(idGenerator.generateEmpId()
				                   ,dto.getName()
				                   ,dto.getNameEng()
				                   ,dto.getNameChi()
				                   ,dto.getResidentRegistrationNumber());
		
		employeeRepository.saveEmployee(emp);
	}
	
	public void deleteEmployee(String id) {		
		employeeRepository.deleteEmployee(id);
	}
	
	public void saveDeptChangeHistory(EmployeeDTO.NewDept dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());
						
		DeptChangeHistory deptChangeHistory = new DeptChangeHistory(emp
																   ,dto.getDeptType()
																   ,dto.getDeptCode()
																   ,new DatePeriod(dto.getFromDate(),dto.getToDate()));
				
		emp.getDeptHistory().add(deptChangeHistory);
		
		employeeRepository.saveEmployee(emp);
	}
	
	public void saveJobChangeHistory(EmployeeDTO.NewJob dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());			
		
		JobChangeHistory jobChangeHistory = new JobChangeHistory(emp
																,dto.getJobType()
																,dto.getJobCode()
																,new DatePeriod(dto.getFromDate(),dto.getToDate()));
		emp.getJobHistory().add(jobChangeHistory);
		
		employeeRepository.saveEmployee(emp);
	}
	
	public void saveStatusChangeHistory(EmployeeDTO.NewStatus dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());
		
		StatusChangeHistory statusChangeHistory = new StatusChangeHistory(emp
																		 ,dto.getAppointmentCode()
																		 ,dto.getStatusCode()
																		 ,new DatePeriod(dto.getFromDate(),dto.getToDate())); 			
		emp.getStatusHistory().add(statusChangeHistory);
		
		employeeRepository.saveEmployee(emp);
	}	
	
	private Employee getEmployeeInfo(String empId) {
		Employee emp = employeeRepository.getEmployee(empId);
		
		if (emp == null) {
			throw new IllegalArgumentException(empId + " 사번이 존재하지 않습니다.");
		}
		
		return emp;
	}
}
