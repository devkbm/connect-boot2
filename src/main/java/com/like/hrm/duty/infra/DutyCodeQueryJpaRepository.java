package com.like.hrm.duty.infra;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.duty.boundary.DutyCodeDTO.SearchDutyCode;
import com.like.hrm.duty.domain.model.DutyCode;
import com.like.hrm.duty.domain.model.QDutyCode;
import com.like.hrm.duty.domain.repository.DutyCodeQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DutyCodeQueryJpaRepository implements DutyCodeQueryRepository {

	private JPAQueryFactory	queryFactory;
	
	public DutyCodeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<DutyCode> getDutyCodeList(SearchDutyCode condition) {
		return queryFactory
				.selectFrom(QDutyCode.dutyCode1)
				.where(condition.getBooleanBuilder())
				.fetch();
	}
}
