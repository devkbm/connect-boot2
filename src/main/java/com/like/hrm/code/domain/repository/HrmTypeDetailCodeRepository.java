package com.like.hrm.code.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.code.domain.model.HrmTypeDetailCode;

@Repository
public interface HrmTypeDetailCodeRepository extends JpaRepository<HrmTypeDetailCode, String> {

}
