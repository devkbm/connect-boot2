package com.like.hrm.duty.infra.spingdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.hrm.duty.domain.model.DutyCodeRule;

public interface JpaDutyCodeRule extends JpaRepository<DutyCodeRule, String>, QuerydslPredicateExecutor<DutyCodeRule> {

}
