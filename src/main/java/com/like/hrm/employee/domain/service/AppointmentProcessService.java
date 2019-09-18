package com.like.hrm.employee.domain.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.like.hrm.appointment.domain.event.ProcessEvent;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.repository.AppointmentRepository;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppointmentProcessService implements ApplicationListener<ProcessEvent> {

	private AppointmentRepository appointmentRepository;
	
	private EmployeeRepository employeeRepository;
		
	public void Appoinment(String AppointmentId) {
		/*
		AppointmentMemorandum memorandum = appointmentRepository.getAppointmentMemorandum();
				
		List<AppointmentDetails> appointmentList = memorandum.getDetails();
		
		for (AppointmentDetails appointment : appointmentList) {
			
		}
		*/
			
	}

	@Override
	public void onApplicationEvent(ProcessEvent event) {
		
		Ledger ledger = null; // appointmentRepository.getAppointmentCode(event.getAppointmentId());
		
		/*for ( LedgerList detail : ledger.getAppointmentList() ) {
			
			Employee emp = employeeRepository.getEmployee(detail.getEmpId());
			
			emp.appoint(detail);
			
		}*/
		
		
	}
	
	
}
