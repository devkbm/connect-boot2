package com.like.hrm.appointment.domain.model;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class AppointmentLedgerDetail {

	private String empId;
		
	private String type;
	
	private LocalDate AppointmentFromDate;
	
	private LocalDate AppointmentToDate = LocalDate.of(9999, 12, 31);
		
}
