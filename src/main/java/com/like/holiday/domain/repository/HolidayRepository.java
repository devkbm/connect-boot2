package com.like.holiday.domain.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.like.holiday.domain.model.Holiday;

public interface HolidayRepository extends JpaRepository<Holiday, LocalDate>, QuerydslPredicateExecutor<Holiday> {
	
}
