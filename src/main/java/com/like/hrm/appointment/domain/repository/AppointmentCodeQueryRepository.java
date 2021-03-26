package com.like.hrm.appointment.domain.repository;

import java.util.List;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;

public interface AppointmentCodeQueryRepository {

	/**
	 * 발령코드 명단을 조회한다.
	 * @param condition
	 * @return
	 */
	List<AppointmentCode> getAppointmentCodeList(AppointmentCodeDTO.SearchCode dto);
	
	/**
	 * 발령코드 상세정보 명단을 조회한다.
	 * @param dto
	 * @return
	 */
	List<AppointmentCodeDetail> getAppointmentCodeDetailList(AppointmentCodeDTO.SearchCodeDetail dto);
}
