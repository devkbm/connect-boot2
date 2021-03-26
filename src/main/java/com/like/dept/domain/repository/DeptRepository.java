package com.like.dept.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.dept.domain.model.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept, String>, QuerydslPredicateExecutor<Dept> {
				
}