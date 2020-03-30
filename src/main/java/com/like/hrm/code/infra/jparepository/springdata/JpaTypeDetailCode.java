package com.like.hrm.code.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.code.domain.model.HrmTypeDetailCode;

@Repository
public interface JpaTypeDetailCode extends JpaRepository<HrmTypeDetailCode, String> {

}
