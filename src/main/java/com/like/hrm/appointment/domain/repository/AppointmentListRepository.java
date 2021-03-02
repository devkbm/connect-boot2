package com.like.hrm.appointment.domain.repository;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.LedgerList;

@Repository
public interface AppointmentListRepository {

	LedgerList get(Long id);
	
	void save(LedgerList entity);
	
	void delete(LedgerList entity);	
}
