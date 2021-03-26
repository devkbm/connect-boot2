package com.like.hrm.appointment.domain.repository;

import java.util.List;

import com.like.hrm.appointment.boundary.AppointmentListDTO;
import com.like.hrm.appointment.boundary.QueryAppointmentList;
import com.like.hrm.appointment.domain.model.AppointmentList;

public interface AppointmentListQueryRepository {

	List<AppointmentList> getList(AppointmentListDTO.SearchAppointmentList searchCondition);
	
	List<QueryAppointmentList> getListDTO(AppointmentListDTO.SearchAppointmentList searchCondition);
}
