package com.like.hrm.appointment.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import lombok.Getter;

@Getter
public class LedgerList {

	Long listId;
	
	String empId;
		
	String appointmentCode;
	
	LocalDate AppointmentFromDate;
	
	LocalDate AppointmentToDate = LocalDate.of(9999, 12, 31);
	
	List<LedgerChangeInfo> changeInfoList = new ArrayList<>();
}
