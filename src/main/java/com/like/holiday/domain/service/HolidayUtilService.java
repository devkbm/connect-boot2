package com.like.holiday.domain.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.like.holiday.domain.model.DateInfo;
import com.like.holiday.domain.model.Holiday;
import com.like.holiday.domain.repository.HolidayRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HolidayUtilService {

	HolidayRepository holidayRepository;
	
	public HolidayUtilService(HolidayRepository holidayRepository) {
		this.holidayRepository = holidayRepository;
	}
	
	public List<DateInfo> getDateList(LocalDate fromDate, LocalDate toDate) {
		
		List<DateInfo> list = this.getDateInfoList(fromDate, toDate);
		
		this.setHoliday(fromDate, toDate, list);
		
		return list;
	}
	
	private List<DateInfo> getDateInfoList(LocalDate fromDate, LocalDate toDate) {
		if (fromDate.isAfter(toDate)) 
			throw new IllegalArgumentException("종료일자보다 시작일자가 큽니다.");
		
		List<DateInfo> list = new ArrayList<>();			
		
		while (fromDate.isBefore(toDate) || fromDate.isEqual(toDate)) {
			list.add(new DateInfo(fromDate));			
			fromDate = fromDate.plusDays(1);
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
