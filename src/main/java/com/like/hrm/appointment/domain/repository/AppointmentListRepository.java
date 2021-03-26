package com.like.hrm.appointment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.AppointmentList;

@Repository
public interface AppointmentListRepository extends JpaRepository<AppointmentList, Long> {		

}
