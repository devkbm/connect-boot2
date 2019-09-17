package com.like.hrm.appointment.domain.model;

import com.like.hrm.appointment.domain.model.enums.ChangeType;

public class LedgerChangeInfo {

	/**
	 * 
	 */
	String id;
		
	/**
	 * 직원 ID
	 */
	String empId;	
	
	ChangeType changeType;
	
	String value;	
}
