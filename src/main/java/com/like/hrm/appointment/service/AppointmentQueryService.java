package com.like.hrm.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.repository.AppointmentLedgerRepository;

@Service
@Transactional(readOnly = true)
public class AppointmentQueryService {

	private AppointmentLedgerRepository appointmentLedgerRepository;
	
	public AppointmentQueryService(AppointmentLedgerRepository appointmentLedgerRepository) {		
		this.appointmentLedgerRepository = appointmentLedgerRepository;
	}
	
	public List<Ledger> getLedger(LedgerDTO.SearchLedger searchCondition) {
		return appointmentLedgerRepository.getLedger(searchCondition);
	}
	
	public List<LedgerList> getLedgerList(LedgerDTO.SearchLedgerList searchCondition) {					
		return appointmentLedgerRepository.getLedgerList(searchCondition);
	}
}
