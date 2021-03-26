package com.like.hrm.code.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.code.domain.model.HrmRelationCode;

@Repository
public interface HrmRelationCodeRepository extends JpaRepository<HrmRelationCode, Long> {

}
