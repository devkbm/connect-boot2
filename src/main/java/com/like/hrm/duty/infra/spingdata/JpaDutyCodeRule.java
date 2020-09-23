package com.like.hrm.duty.infra.spingdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.hrm.duty.domain.model.DutyCodeLimitRule;

public interface JpaDutyCodeRule extends JpaRepository<DutyCodeLimitRule, String>, QuerydslPredicateExecutor<DutyCodeLimitRule> {

}
