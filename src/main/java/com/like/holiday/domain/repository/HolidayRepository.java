package com.like.holiday.domain.repository;

import java.time.LocalDate;

import com.like.holiday.domain.model.Holiday;

public interface HolidayRepository {

	Holiday getHoliday(LocalDate date);

	void saveHoliday(Holiday entity);
	
	void deleteHoliday(Holiday entity);
}
