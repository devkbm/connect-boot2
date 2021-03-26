package com.like.workschedule.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.workschedule.boundary.WorkDTO.SearchWorkGroup;
import com.like.workschedule.domain.model.QWorkGroup;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.repository.WorkGroupQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class WorkGroupQueryJpaRepository implements WorkGroupQueryRepository {
	private JPAQueryFactory queryFactory;
	private final QWorkGroup qWorkGroup = QWorkGroup.workGroup;
	
	public WorkGroupQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<WorkGroup> getWorkGroupList(SearchWorkGroup searchCondition) {
		return queryFactory
				.selectFrom(qWorkGroup)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}
	
	
}
