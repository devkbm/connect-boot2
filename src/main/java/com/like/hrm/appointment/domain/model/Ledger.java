package com.like.hrm.appointment.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Ledger {

	/**
	 * 발령 번호
	 */
	String AppointmentId;
		
	/**
	 * 발령 유형(정기, 임의)
	 */
	String AppointmentType;
	
	LocalDate registrationDate;
		
	List<LedgerList> appointmentList = new ArrayList<>();
				
}
