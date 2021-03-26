package com.like.hrm.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentRegisterDTO;
import com.like.hrm.appointment.domain.model.AppointmentRegister;
import com.like.hrm.appointment.domain.repository.AppointmentRegisterQueryRepository;

@Service
@Transactional(readOnly = true)
public class AppointmentRegisterQueryService {

	private AppointmentRegisterQueryRepository repository;
	
	public AppointmentRegisterQueryService(AppointmentRegisterQueryRepository repository) {		
		this.repository = repository;
	}
	
	public List<AppointmentRegister> getLedger(AppointmentRegisterDTO.SearchAppointmentRegister searchCondition) {
		return repository.getList(searchCondition);
	}
		
}
