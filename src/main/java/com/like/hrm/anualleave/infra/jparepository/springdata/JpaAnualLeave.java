package com.like.hrm.anualleave.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;

@Repository
public interface JpaAnualLeave extends JpaRepository<AnualLeave, AnualLeaveId>, QuerydslPredicateExecutor<AnualLeave> {

}
