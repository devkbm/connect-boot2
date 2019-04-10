package com.like.hrm.appointment.domain.event;

import org.springframework.context.ApplicationEvent;

import com.like.hrm.appointment.domain.model.AppointmentLedger;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class ProcessEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = -4365056669864989318L;
	
	private String appointmentId;
	
	public ProcessEvent(Object source, AppointmentLedger appointmentLedger) {
		super(source);		
		this.appointmentId = appointmentLedger.getAppointmentId();
	}

	public String getAppointmentId() {
		return appointmentId;
	}
	
}
