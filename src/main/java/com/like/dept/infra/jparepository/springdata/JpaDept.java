package com.like.dept.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.dept.domain.model.Dept;

@Repository
public interface JpaDept extends JpaRepository<Dept, String>, QuerydslPredicateExecutor<Dept> {

}
