package com.like.commoncode.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.commoncode.domain.model.Code;

@Repository
public interface CommonCodeRepository extends JpaRepository<Code, String>, QuerydslPredicateExecutor<Code> {		
		
}
