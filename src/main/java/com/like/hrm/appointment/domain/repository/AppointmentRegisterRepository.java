package com.like.hrm.appointment.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentRegisterDTO;
import com.like.hrm.appointment.domain.model.AppointmentRegister;

@Repository
public interface AppointmentRegisterRepository {		
	
	AppointmentRegister get(String id);
	
	void save(AppointmentRegister ledger);
	
	void delete(AppointmentRegister ledger);	
		
	List<AppointmentRegister> getList(AppointmentRegisterDTO.SearchAppointmentRegister searchCondition);
}
