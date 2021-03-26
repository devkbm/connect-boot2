package com.like.hrm.duty.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.hrm.duty.domain.model.DutyCode;

public interface DutyCodeRepository extends JpaRepository<DutyCode, String>, QuerydslPredicateExecutor<DutyCode> {

}
