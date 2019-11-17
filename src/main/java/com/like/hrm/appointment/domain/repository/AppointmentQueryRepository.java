package com.like.hrm.appointment.domain.repository;

import java.util.List;

import com.like.hrm.appointment.boundary.AppointmentCodeDTO;
import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;

public interface AppointmentQueryRepository {
	
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
	
	List<Ledger> getLedger(LedgerDTO.SearchLedger searchCondition);
	
	List<LedgerList> getLedgerList(LedgerDTO.SearchLedgerList searchCondition);
		
}
