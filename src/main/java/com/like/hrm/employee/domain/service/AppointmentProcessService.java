package com.like.hrm.employee.domain.service;

import java.time.LocalDate;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.like.hrm.appointment.domain.event.AppointmentProcessEvent;
import com.like.hrm.appointment.domain.model.LedgerChangeInfo;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppointmentProcessService {

	private EmployeeRepository employeeRepository;
		
	public AppointmentProcessService(EmployeeRepository employeeRepository) {		
		this.employeeRepository = employeeRepository;		
	}
		
	@EventListener
	public void onApplicationEvent(AppointmentProcessEvent event) {
						
		LedgerList list = event.getLedgerList();
		
		Employee employee = employeeRepository.getEmployee(list.getEmpId());

		for (LedgerChangeInfo ledgerChangeInfo : list.getChangeInfoList()) {
								
			if (ChangeType.DEPT.equals(ledgerChangeInfo.getChangeType())) {
				AppointDeptInfo(employee, list.getAppointmentFromDate(), ledgerChangeInfo);
				
			} else if (ChangeType.JOB.equals(ledgerChangeInfo.getChangeType())) {
			
			} 
		}
							
	}
	
	private void AppointDeptInfo(Employee employee, LocalDate appointmentDate, LedgerChangeInfo info) {
		for ( DeptChangeHistory history : employee.getDeptHistory() ) {
			
			if (info.getChangeTypeDetail().equals(history.getDeptType())) {
				
				employee.terminateDept(info.getChangeTypeDetail(), appointmentDate.minusDays(1));								
			}
		}
		
	}
	
	
}
