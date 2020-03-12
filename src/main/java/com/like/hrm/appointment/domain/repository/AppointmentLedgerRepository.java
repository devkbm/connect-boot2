package com.like.hrm.appointment.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.boundary.LedgerDTO;
import com.like.hrm.appointment.domain.model.Ledger;
import com.like.hrm.appointment.domain.model.LedgerList;

@Repository
public interface AppointmentLedgerRepository {
		
	List<Ledger> getLedger(LedgerDTO.SearchLedger searchCondition);
	
	List<LedgerList> getLedgerList(LedgerDTO.SearchLedgerList searchCondition);
	
	Ledger getLedger(String id);
	
	void saveLedger(Ledger ledger);
	
	void deleteLedger(Ledger ledger);	
	
}
