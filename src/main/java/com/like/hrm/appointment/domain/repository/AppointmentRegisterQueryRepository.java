package com.like.hrm.appointment.domain.repository;

import java.util.List;

import com.like.hrm.appointment.boundary.AppointmentRegisterDTO;
import com.like.hrm.appointment.domain.model.AppointmentRegister;

public interface AppointmentRegisterQueryRepository {

	List<AppointmentRegister> getList(AppointmentRegisterDTO.SearchAppointmentRegister searchCondition);
}
