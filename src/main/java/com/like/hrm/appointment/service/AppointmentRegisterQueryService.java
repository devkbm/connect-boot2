package com.like.hrm.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentRegisterDTO;
import com.like.hrm.appointment.domain.model.AppointmentRegister;
import com.like.hrm.appointment.domain.repository.AppointmentRegisterRepository;

@Service
@Transactional(readOnly = true)
public class AppointmentRegisterQueryService {

	private AppointmentRegisterRepository repository;
	
	public AppointmentRegisterQueryService(AppointmentRegisterRepository appointmentRegisterRepository) {		
		this.repository = appointmentRegisterRepository;
	}
	
	public List<AppointmentRegister> getLedger(AppointmentRegisterDTO.SearchAppointmentRegister searchCondition) {
		return repository.getList(searchCondition);
	}
		
}
