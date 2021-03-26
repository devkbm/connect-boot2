package com.like.holiday.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.like.holiday.domain.model.Holiday;

public interface HolidayQueryRepository {

	List<Holiday> getHoliday(LocalDate fromDate, LocalDate toDate);
}
