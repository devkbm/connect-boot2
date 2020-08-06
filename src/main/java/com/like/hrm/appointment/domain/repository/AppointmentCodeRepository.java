package com.like.hrm.appointment.domain.repository;

import java.util.List;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;

public interface AppointmentCodeRepository {	
	
	
	boolean isAppointmentCode(String codeId);
	
	/**
	 * 발령코드를 조회한다.
	 * @param codeId
	 * @return
	 */
	AppointmentCode getAppointmentCode(String codeId);
	
	/**
	 * 발령코드 명단을 조회한다.
	 * @param condition
	 * @return
	 */
	List<AppointmentCode> getAppointmentCodeList(AppointmentCodeDTO.SearchCode dto);	
	
	/**
	 * 발령코드를 저장한다.
	 * @param appointmentCode
	 */
	void saveAppintmentCode(AppointmentCode appointmentCode);
	
	/**
	 * 발령코드를 삭제한다.
	 * @param appointmentCode
	 */
	void deleteAppintmentCode(AppointmentCode appointmentCode);
	
	/**
	 * 발령코드 상세정보 명단을 조회한다.
	 * @param dto
	 * @return
	 */
	List<AppointmentCodeDetail> getAppointmentCodeDetailList(AppointmentCodeDTO.SearchCodeDetail dto);
}
