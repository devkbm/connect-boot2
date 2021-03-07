package com.like.hrm.appointment.domain.event;

import com.like.hrm.appointment.domain.model.AppointmentList;

import lombok.Data;

@Data
public class AppointmentProcessEvent {
	
	private static final long serialVersionUID = -4365056669864989318L;
	
	private Object source;
	
	private AppointmentList appointment; 	
	
	public AppointmentProcessEvent(Object source, AppointmentList appointment) {
		this.source = source;
		this.appointment = appointment;
	}
	
	
}
