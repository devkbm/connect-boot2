package com.like.hrm.appointment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.hrm.appointment.domain.model.AppointmentCode;

public interface AppointmentCodeRepository extends JpaRepository<AppointmentCode, String>, QuerydslPredicateExecutor<AppointmentCode> {			
	
}
