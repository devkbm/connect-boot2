package com.like.hrm.employee.domain.service;

import java.time.LocalDate;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.like.hrm.appointment.domain.event.AppointmentProcessEvent;
import com.like.hrm.appointment.domain.model.LedgerChangeInfo;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.JobChangeHistory;
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
		LedgerList list = event.getLedgerList();		
		Employee employee = employeeRepository.getEmployee(list.getEmpId());

		for (LedgerChangeInfo ledgerChangeInfo : list.getChangeInfoList()) {
								
			if (ChangeType.DEPT.equals(ledgerChangeInfo.getChangeType())) {				
				appointDeptInfo(employee
							   ,ledgerChangeInfo
							   ,list.getAppointmentFromDate()
							   ,list.getAppointmentToDate());
				
			} else if (ChangeType.JOB.equals(ledgerChangeInfo.getChangeType())) {
				appointJobInfo(employee
							  ,ledgerChangeInfo
							  ,list.getAppointmentFromDate()
							  ,list.getAppointmentToDate());
			} 
			
		}
		
		list.finish();
							
	}
	
	private void appointDeptInfo(Employee employee, LedgerChangeInfo info, LocalDate appointmentFromDate, LocalDate appointmentToDate) {				
		employee.addDeptChange(
				new DeptChangeHistory(employee
									 ,info.getChangeTypeDetail()
									 ,info.getChangeCode()
									 ,appointmentFromDate
									 ,appointmentToDate)
				);
	}
	
	private void appointJobInfo(Employee employee, LedgerChangeInfo info, LocalDate appointmentFromDate, LocalDate appointmentToDate) {
		employee.addJobChange(
				new JobChangeHistory(employee
									 ,info.getChangeTypeDetail()
									 ,info.getChangeCode()
									 ,appointmentFromDate
									 ,appointmentToDate)
				);	
	}
	
	
}
