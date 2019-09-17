package com.like.hrm.appointment.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Ledger {

	/**
	 * 원장 번호
	 */
	String LedgerId;
		
	/**
	 * 발령 유형(정기, 임의)
	 */
	String AppointmentType;
	
	/**
	 * 등록일
	 */
	LocalDate registrationDate;
		
	/**
	 * 발령 명단
	 */
	List<LedgerList> appointmentList = new ArrayList<>();
				
}
