package com.like.hrm.employee.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.like.common.vo.DatePeriod;
import com.like.hrm.appointment.domain.event.AppointmentProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentChangeInfo;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentList;
import com.like.hrm.appointment.domain.repository.AppointmentCodeRepository;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;
import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.JobChangeHistory;
import com.like.hrm.employee.domain.model.StatusChangeHistory;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

@Service
public class AppointmentProcessService {

	private EmployeeRepository employeeRepository;
		
	private AppointmentCodeRepository appointmentCodeRepository;
	
	public AppointmentProcessService(EmployeeRepository employeeRepository,
									 AppointmentCodeRepository appointmentCodeRepository) {		
		this.employeeRepository = employeeRepository;		
		this.appointmentCodeRepository = appointmentCodeRepository;
	}
		
	//@EventListener
	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	public void onApplicationEvent(AppointmentProcessEvent event) {	
				
		AppointmentList list = event.getAppointment();		
		Employee employee = employeeRepository.findById(list.getEmpId()).orElse(null);			
		AppointmentCode code = appointmentCodeRepository.findById(list.getAppointmentCode()).orElse(null);
		
		// 1. 인사정보(부서, 직위 등)을 적용한다.
		appoint(list);
		
		// 2. 근무상태 정보를 적용한다.
		if (code.getEmploymentStatus() != null) {
			this.addStatusInfo(employee, list.getAppointmentCode(), code.getCode(), list.getAppointmentFromDate(), list.getAppointmentToDate());
		}
				
		list.finish();
							
	}
	
	private void appoint(AppointmentList list) {		
		Employee employee = employeeRepository.findById(list.getEmpId()).orElse(null);
		
		for (AppointmentChangeInfo changeInfo : list.getChangeInfoList()) {
			
			if (HrmTypeEnum.DEPT.equals(changeInfo.getChangeType())) {				
				appointDeptInfo(employee
							   ,changeInfo
							   ,list.getAppointmentFromDate()
							   ,list.getAppointmentToDate());
				
			} else if (HrmTypeEnum.JOB.equals(changeInfo.getChangeType())) {
				appointJobInfo(employee
							  ,changeInfo
							  ,list.getAppointmentFromDate()
							  ,list.getAppointmentToDate());
			} 							
		}
		
	}
	
	
	private void appointDeptInfo(Employee employee, AppointmentChangeInfo info, LocalDate appointmentFromDate, LocalDate appointmentToDate) {				
		employee.getDeptHistory().add(
				new DeptChangeHistory(employee
									 ,info.getChangeTypeDetail()
									 ,info.getChangeCode()
									 ,new DatePeriod(appointmentFromDate,appointmentToDate))
		);
	}
	
	private void appointJobInfo(Employee employee, AppointmentChangeInfo info, LocalDate appointmentFromDate, LocalDate appointmentToDate) {
		employee.getJobHistory().add(
				new JobChangeHistory(employee
									 ,info.getChangeTypeDetail()
									 ,info.getChangeCode()
									 ,new DatePeriod(appointmentFromDate,appointmentToDate))
		);	
	}
	
	private void addStatusInfo(Employee employee, String appointmentCode, String statusCode, LocalDate appointmentFromDate, LocalDate appointmentToDate) {
		employee.getStatusHistory().add(
				new StatusChangeHistory(employee
									   ,appointmentCode
									   ,statusCode
									   ,new DatePeriod(appointmentFromDate,appointmentToDate))
		);
	}
	
	
}
