package com.like.hrm.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCodeDetail;
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.repository.AppointmentRepository;

@Service
@Transactional(readOnly = true)
public class AppointmentQueryService {

	private AppointmentRepository appointmentRepository;
	
	public AppointmentQueryService(AppointmentRepository appointmentRepository) {		
		this.appointmentRepository = appointmentRepository;
	}
	
	public List<AppointmentCode> getAppointentCodeList(AppointmentCodeDTO.SearchCode search) {
		return appointmentRepository.getAppointmentCodeList(search);
	}
	
	public List<AppointmentCodeDetail> getAppointmentCodeDetailList(SearchCodeDetail dto) {
		return appointmentRepository.getAppointmentCodeDetailList(dto);
	}
	
	public List<LedgerList> getLedgerList(LedgerDTO.SearchLedgerList searchCondition) {
		return null;
	}
}
