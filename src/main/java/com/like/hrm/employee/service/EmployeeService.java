package com.like.hrm.employee.service;

import javax.persistence.EntityNotFoundException;

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

@Service
@Transactional
public class EmployeeService {
	
	private EmployeeRepository repository;	
	private EmployeeIdGenerator idGenerator;
		
	public EmployeeService(EmployeeRepository repository, EmployeeIdGenerator idGenerator) {
		this.repository = repository;
		this.idGenerator = idGenerator;
	}	
	
	public Employee getEmployee(String id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveEmployee(Employee employee) {				
		repository.save(employee);
	}
	
	public void saveEmployee(EmployeeDTO.SaveEmployee dto) {
		Employee employee = this.getEmployee(dto.getId());
		
		dto.modifyEntity(employee);
		
		repository.save(employee);
	}
	
	public void newEmployee(EmployeeDTO.NewEmployee dto) {										
		
		Employee emp = new Employee(idGenerator.generateEmpId()
				                   ,dto.getName()
				                   ,dto.getNameEng()
				                   ,dto.getNameChi()
				                   ,dto.getResidentRegistrationNumber());
		
		repository.save(emp);
	}
	
	public void deleteEmployee(String id) {		
		repository.deleteById(id);
	}
	
	public void saveDeptChangeHistory(EmployeeDTO.NewDept dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());
						
		DeptChangeHistory deptChangeHistory = new DeptChangeHistory(emp
																   ,dto.getDeptType()
																   ,dto.getDeptCode()
																   ,new DatePeriod(dto.getFromDate(),dto.getToDate()));
				
		emp.getDeptHistory().add(deptChangeHistory);
		
		repository.save(emp);
	}
	
	public void saveJobChangeHistory(EmployeeDTO.NewJob dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());			
		
		JobChangeHistory jobChangeHistory = new JobChangeHistory(emp
																,dto.getJobType()
																,dto.getJobCode()
																,new DatePeriod(dto.getFromDate(),dto.getToDate()));
		emp.getJobHistory().add(jobChangeHistory);
		
		repository.save(emp);
	}
	
	public void saveStatusChangeHistory(EmployeeDTO.NewStatus dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());
		
		StatusChangeHistory statusChangeHistory = new StatusChangeHistory(emp
																		 ,dto.getAppointmentCode()
																		 ,dto.getStatusCode()
																		 ,new DatePeriod(dto.getFromDate(),dto.getToDate())); 			
		emp.getStatusHistory().add(statusChangeHistory);
		
		repository.save(emp);
	}	
	
	private Employee getEmployeeInfo(String empId) {
		return repository.findById(empId)
				 .orElseThrow(() -> new EntityNotFoundException(empId + " 사번이 존재하지 않습니다."));
	}
}
