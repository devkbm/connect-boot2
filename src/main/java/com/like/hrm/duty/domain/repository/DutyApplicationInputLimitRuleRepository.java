package com.like.hrm.duty.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.hrm.duty.domain.model.DutyApplicationInputLimitRule;

public interface DutyApplicationInputLimitRuleRepository extends JpaRepository<DutyApplicationInputLimitRule, Long>, QuerydslPredicateExecutor<DutyApplicationInputLimitRule> {
}
