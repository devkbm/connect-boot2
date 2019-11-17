package com.like.hrm.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.AppointmentCodeDTO.SearchCodeDetail;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.repository.AppointmentQueryRepository;

@Service
@Transactional(readOnly = true)
public class AppointmentCodeQueryService {

	private AppointmentQueryRepository appointmentQueryRepository;
	
	public AppointmentCodeQueryService(AppointmentQueryRepository appointmentQueryRepository) {		
		this.appointmentQueryRepository = appointmentQueryRepository;
	}
	
	public List<AppointmentCode> getAppointentCodeList(AppointmentCodeDTO.SearchCode search) {
		return appointmentQueryRepository.getAppointmentCodeList(search);
	}
	
	public List<AppointmentCodeDetail> getAppointmentCodeDetailList(SearchCodeDetail dto) {
		return appointmentQueryRepository.getAppointmentCodeDetailList(dto);
	}
}
