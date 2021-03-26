package com.like.hrm.appointment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.AppointmentRegister;

@Repository
public interface AppointmentRegisterRepository extends JpaRepository<AppointmentRegister, String> {		
	
}
