package com.like.workschedule.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.workschedule.boundary.ScheduleDTO.SearchSchedule;
import com.like.workschedule.domain.model.QSchedule;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.repository.ScheduleQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ScheduleQueryJpaRepository implements ScheduleQueryRepository {
	private JPAQueryFactory queryFactory;
	private final QSchedule qSchedule = QSchedule.schedule;
	
	public ScheduleQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<Schedule> getScheduleList(SearchSchedule searchCondition) {
		return queryFactory
				.selectFrom(qSchedule)
				.where(searchCondition.getBooleanBuilder())
				.fetch();
	}
	
}
