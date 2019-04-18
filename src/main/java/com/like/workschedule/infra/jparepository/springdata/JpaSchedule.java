package com.like.workschedule.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.workschedule.domain.model.Schedule;

@Repository
public interface JpaSchedule extends JpaRepository<Schedule, Long>, QuerydslPredicateExecutor<Schedule> {

}
