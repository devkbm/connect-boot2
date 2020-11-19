package com.like.hrm.payitem.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.hrm.payitem.domain.model.PayItem;

@Repository
public interface JpaPayItem extends JpaRepository<PayItem, String>, QuerydslPredicateExecutor<PayItem> {

}
