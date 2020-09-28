package com.like.holiday.infra.springdata;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.holiday.domain.model.Holiday;

@Repository
public interface JpaHoliday extends JpaRepository<Holiday, LocalDate>, QuerydslPredicateExecutor<Holiday> {

}
