package com.like.workschedule.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.workschedule.domain.model.WorkGroup;

@Repository
public interface WorkGroupRepository extends JpaRepository<WorkGroup, Long>, QuerydslPredicateExecutor<WorkGroup> { 

}
