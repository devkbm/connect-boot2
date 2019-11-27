package com.like.hrm.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.employee.boundary.EmployeeDTO;
import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Education;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.JobChangeHistory;
import com.like.hrm.employee.domain.model.License;
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
																   ,dto.getFromDate()
																   ,dto.getToDate());
				
		emp.addDeptChange(deptChangeHistory);
		
		employeeRepository.saveEmployee(emp);
	}
	
	public void saveJobChangeHistory(EmployeeDTO.NewJob dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());			
		
		JobChangeHistory jobChangeHistory = new JobChangeHistory(emp
																,dto.getJobType()
																,dto.getJobCode()
																,dto.getFromDate()
																,dto.getToDate());
		emp.addJobChange(jobChangeHistory);
		
		employeeRepository.saveEmployee(emp);
	}
	
	public void saveStatusChangeHistory(EmployeeDTO.NewStatus dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());
						
		emp.changeStatus(dto.getAppointmentCode()
						,dto.getStatusCode()
						,dto.getFromDate()
						,dto.getToDate());	
		
		employeeRepository.saveEmployee(emp);
	}
	
	public License getLicense(String empId, Long id) {
		Employee emp = getEmployeeInfo(empId);
						
		return emp.getLicense(id);
	}
	
	public void saveLicense(EmployeeDTO.SaveLicense dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());
		
		License license = emp.getLicense(dto.getLicenseId());
		
		if (license == null) {
			license = dto.newLicense(emp);
		} else {
			dto.modifyLicense(license);
		}
		
		emp.addLicense(license);
		
		employeeRepository.saveEmployee(emp);
	}
	
	public Education getEducation(String empId, Long id) {
		Employee emp = getEmployeeInfo(empId);
		
		return emp.getEducation(id);
	}
	
	public void saveEducation(EmployeeDTO.SaveEducation dto) {
		Employee emp = getEmployeeInfo(dto.getEmployeeId());
		
		Education education = emp.getEducation(dto.getEducationId());
		
		if (education == null) {
			education = dto.newEducation(emp);
		} else {
			dto.modifyEducation(education);
		}
		
		emp.addEducation(education);
		
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
