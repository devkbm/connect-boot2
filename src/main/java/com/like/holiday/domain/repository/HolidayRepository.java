package com.like.holiday.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.like.holiday.domain.model.Holiday;

public interface HolidayRepository {

	List<Holiday> getHoliday(LocalDate fromDate, LocalDate toDate);
	
	Holiday getHoliday(LocalDate date);

	void saveHoliday(Holiday entity);
	
	void deleteHoliday(Holiday entity);	
}
