package com.like.holiday.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.like.holiday.domain.model.DateInfo;
import com.like.holiday.domain.model.Holiday;
import com.like.holiday.domain.repository.HolidayRepository;

public class HolidayUtilService {

	HolidayRepository holidayRepository;
	
	public HolidayUtilService(HolidayRepository holidayRepository) {
		this.holidayRepository = holidayRepository;
	}
	
	public List<DateInfo> getDateList(LocalDate fromDate, LocalDate toDate) {
		
		List<DateInfo> list = new ArrayList<>();
					
		while (fromDate.isBefore(toDate)) {
			list.add(new DateInfo(fromDate));			
			fromDate.plusDays(1);
		}		
		
		return list;
	}
	
	private List<DateInfo> setHoliday(LocalDate fromDate, LocalDate toDate, List<DateInfo> list) {
		List<Holiday> holidayList = holidayRepository.getHoliday(fromDate, toDate);					
		
		for (Holiday holiday : holidayList) {
			list.forEach(e -> {
				if (e.getDate().isEqual(holiday.getDate()))
					e.setHoliday(holiday);
			} );
		}
		
		return list;
	}
}
