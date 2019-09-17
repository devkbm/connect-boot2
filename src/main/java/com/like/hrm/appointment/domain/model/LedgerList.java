package com.like.hrm.appointment.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import lombok.Getter;

@Getter
public class LedgerList {

	/**
	 * 식별자
	 */
	Long listId;
	
	/**
	 * 직원 ID
	 */
	String empId;
		
	/**
	 * 발령코드
	 */
	String appointmentCode;
	
	/**
	 * 발령시작일자
	 */
	LocalDate AppointmentFromDate;
	
	/**
	 * 발령종료일자
	 */
	LocalDate AppointmentToDate = LocalDate.of(9999, 12, 31);
	
	/**
	 * 발령변경정보
	 */
	List<LedgerChangeInfo> changeInfoList = new ArrayList<>();
}
