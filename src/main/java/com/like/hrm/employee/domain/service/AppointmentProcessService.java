package com.like.hrm.employee.domain.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.like.common.vo.DatePeriod;
import com.like.hrm.appointment.domain.event.AppointmentProcessEvent;
import com.like.hrm.appointment.domain.model.AppointmentChangeInfo;
import com.like.hrm.appointment.domain.model.AppointmentList;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;
import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.JobChangeHistory;
import com.like.hrm.employee.domain.model.StatusChangeHistory;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppointmentProcessService {

	private EmployeeRepository employeeRepository;
		
	public AppointmentProcessService(EmployeeRepository employeeRepository) {		
		this.employeeRepository = employeeRepository;		
	}
		
	//@EventListener
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onApplicationEvent(AppointmentProcessEvent event) {					
		AppointmentList list = event.getLedgerList();		
		Employee employee = employeeRepository.getEmployee(list.getEmpId());

		for (AppointmentChangeInfo ledgerChangeInfo : list.getChangeInfoList()) {
								
			if (HrmTypeEnum.DEPT.equals(ledgerChangeInfo.getChangeType())) {				
				appointDeptInfo(employee
							   ,ledgerChangeInfo
							   ,list.getAppointmentFromDate()
							   ,list.getAppointmentToDate());
				
			} else if (HrmTypeEnum.JOB.equals(ledgerChangeInfo.getChangeType())) {
				appointJobInfo(employee
							  ,ledgerChangeInfo
							  ,list.getAppointmentFromDate()
							  ,list.getAppointmentToDate());
			} else if (HrmTypeEnum.STATUS.equals(ledgerChangeInfo.getChangeType())) {
				appointStatusInfo(employee
								 ,ledgerChangeInfo
								 ,list.getAppointmentFromDate()
								 ,list.getAppointmentToDate());
			}
			
		}
		
		list.finish();
							
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
	
	private void appointStatusInfo(Employee employee, AppointmentChangeInfo info, LocalDate appointmentFromDate, LocalDate appointmentToDate) {
		employee.getStatusHistory().add(
				new StatusChangeHistory(employee
									   ,info.getLedgerList().getAppointmentCode()
									   ,info.getChangeCode()
									   ,new DatePeriod(appointmentFromDate,appointmentToDate))
		);
	}
	
	
}
