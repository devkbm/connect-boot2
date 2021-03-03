package com.like.hrm.appointment.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.AppointmentRegister;

@Repository
public interface JpaLedger extends JpaRepository<AppointmentRegister, String> {

}
