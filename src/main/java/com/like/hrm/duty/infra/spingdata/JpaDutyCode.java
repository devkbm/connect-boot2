package com.like.hrm.duty.infra.spingdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.duty.domain.model.DutyCode;

@Repository
public interface JpaDutyCode extends JpaRepository<DutyCode, String> {

}
