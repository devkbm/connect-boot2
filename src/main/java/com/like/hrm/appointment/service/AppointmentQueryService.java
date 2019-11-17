package com.like.hrm.appointment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;
import com.like.hrm.appointment.domain.repository.AppointmentQueryRepository;

@Service
@Transactional(readOnly = true)
public class AppointmentQueryService {

	private AppointmentQueryRepository appointmentQueryRepository;
	
	public AppointmentQueryService(AppointmentQueryRepository appointmentQueryRepository) {		
		this.appointmentQueryRepository = appointmentQueryRepository;
	}
	
	public List<Ledger> getLedger(LedgerDTO.SearchLedger searchCondition) {
		return appointmentQueryRepository.getLedger(searchCondition);
	}
	
	public List<LedgerList> getLedgerList(LedgerDTO.SearchLedgerList searchCondition) {					
		return appointmentQueryRepository.getLedgerList(searchCondition);
	}
}
