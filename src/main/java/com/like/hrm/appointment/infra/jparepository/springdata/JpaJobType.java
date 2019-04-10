package com.like.hrm.appointment.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.model.JobType;

@Repository
public interface JpaJobType extends JpaRepository<JobType, String> {

}
