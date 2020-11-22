package com.like.hrm.payitem.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.hrm.payitem.domain.model.PayTable;

@Repository
public interface JpaPayTable extends JpaRepository<PayTable,Long>, QuerydslPredicateExecutor<PayTable> {

}
