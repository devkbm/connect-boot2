package com.like.hrm.appointment.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.boundary.QueryAppointmentList;
import com.like.hrm.appointment.domain.model.AppointmentList;

@Repository
public interface AppointmentListRepository {

	AppointmentList get(Long id);
	
	void save(AppointmentList entity);
	
	void delete(AppointmentList entity);	
	
	List<AppointmentList> getList(AppointmentListDTO.SearchAppointmentList searchCondition);
	
	List<QueryAppointmentList> getListDTO(AppointmentListDTO.SearchAppointmentList searchCondition);
}
