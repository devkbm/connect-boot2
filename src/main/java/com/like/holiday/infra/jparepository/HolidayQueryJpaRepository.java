package com.like.holiday.infra.jparepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.holiday.domain.model.Holiday;
import com.like.holiday.domain.model.QHoliday;
import com.like.holiday.domain.repository.HolidayQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class HolidayQueryJpaRepository implements HolidayQueryRepository {

	private JPAQueryFactory  queryFactory;
	private static final QHoliday qHoliday = QHoliday.holiday;
	
	public HolidayQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<Holiday> getHoliday(LocalDate fromDate, LocalDate toDate) {		
		
		return queryFactory
				.selectFrom(qHoliday)
				.where(qHoliday.date.goe(fromDate).and(qHoliday.date.loe(toDate)))
				.fetch();				
	}

}
