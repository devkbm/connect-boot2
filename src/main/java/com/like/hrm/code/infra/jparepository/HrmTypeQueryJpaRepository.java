package com.like.hrm.code.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.code.boundary.HrmTypeDTO.SearchHrmType;
import com.like.hrm.code.boundary.HrmTypeDetailCodeDTO.SearchHrmTypeDetailCode;
import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.HrmTypeDetailCode;
import com.like.hrm.code.domain.model.QHrmType;
import com.like.hrm.code.domain.model.QHrmTypeDetailCode;
import com.like.hrm.code.domain.repository.HrmTypeQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HrmTypeQueryJpaRepository implements HrmTypeQueryRepository {

	private JPAQueryFactory	queryFactory;
	private static final QHrmType qHrmType = QHrmType.hrmType1;
	private static final QHrmTypeDetailCode qHrmTypeDetailCode = QHrmTypeDetailCode.hrmTypeDetailCode;
	
	public HrmTypeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<HrmType> getHrmTypeList(SearchHrmType condition) {
		return queryFactory
				.selectFrom(qHrmType)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<HrmTypeDetailCode> getTypeDetailCodeList(SearchHrmTypeDetailCode condition) {
		return queryFactory
				.selectFrom(qHrmTypeDetailCode)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

}
