package com.like.hrm.appointment.domain.event;

import com.like.hrm.appointment.domain.model.LedgerList;

import lombok.Data;

@Data
public class AppointmentProcessEvent {
	
	private static final long serialVersionUID = -4365056669864989318L;
	
	private Object source;
	
	private LedgerList ledgerList; 	
	
	public AppointmentProcessEvent(Object source, LedgerList ledgerList) {
		this.source = source;
		this.ledgerList = ledgerList;
	}
	
	
}
