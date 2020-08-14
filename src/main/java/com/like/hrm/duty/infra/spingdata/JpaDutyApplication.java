package com.like.hrm.duty.infra.spingdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.hrm.duty.domain.model.DutyApplication;

@Repository
public interface JpaDutyApplication extends JpaRepository<DutyApplication, Long>, QuerydslPredicateExecutor<DutyApplication> {

}
