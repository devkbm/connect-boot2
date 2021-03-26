package com.like.hrm.duty.domain.repository;

import java.util.List;

import com.like.hrm.duty.boundary.DutyCodeDTO;
import com.like.hrm.duty.domain.model.DutyCode;

public interface DutyCodeQueryRepository {
	List<DutyCode> getDutyCodeList(DutyCodeDTO.SearchDutyCode condition);
}
