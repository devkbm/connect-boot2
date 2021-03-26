package com.like.hrm.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.boundary.QueryAppointmentList;
import com.like.hrm.appointment.domain.model.AppointmentList;
import com.like.hrm.appointment.domain.repository.AppointmentListQueryRepository;

@Service
@Transactional(readOnly = true)
public class AppointmentListQueryService {

	private AppointmentListQueryRepository repository;
	
	public AppointmentListQueryService(AppointmentListQueryRepository repository) {
		this.repository = repository;		
	}
	
	public List<AppointmentList> getList(AppointmentListDTO.SearchAppointmentList searchCondition) {					
		return repository.getList(searchCondition);
	}
	
	public List<QueryAppointmentList> getListDTO(AppointmentListDTO.SearchAppointmentList searchCondition) {					
		return repository.getListDTO(searchCondition);
	}
}
