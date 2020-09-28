package com.like.holiday.infra;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.like.holiday.domain.model.Holiday;
import com.like.holiday.domain.model.QHoliday;
import com.like.holiday.domain.repository.HolidayRepository;
import com.like.holiday.infra.springdata.JpaHoliday;

@Repository
public class HolidayJpaRepository implements HolidayRepository {

	private JpaHoliday jpaHoliday;
	
	public HolidayJpaRepository(JpaHoliday jpaHoliday) {
		this.jpaHoliday = jpaHoliday;
	}
	
	@Override
	public List<Holiday> getHoliday(LocalDate fromDate, LocalDate toDate) {
		QHoliday qHoliday = QHoliday.holiday;
		
		return Lists.newArrayList(jpaHoliday.findAll(qHoliday.date.goe(fromDate).and(qHoliday.date.loe(toDate))));
	}

	@Override
	public Holiday getHoliday(LocalDate date) {
		return this.jpaHoliday.findById(date).orElse(null);
	}

	@Override
	public void saveHoliday(Holiday entity) {
		this.jpaHoliday.save(entity);		
	}

	@Override
	public void deleteHoliday(Holiday entity) {
		this.jpaHoliday.delete(entity);
	}
	
}
