package com.like.holiday.domain.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.stereotype.Service;

import com.like.holiday.domain.model.DateInfo;
import com.like.holiday.domain.model.DateInfoList;
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
	
	public DateInfoList getDateInfoList(LocalDate fromDate, LocalDate toDate) {
		List<DateInfo> days = this.getRawDateInfoList(fromDate, toDate);
		
		List<Holiday> holidays = this.getHolidayList(fromDate, toDate);
		
		return new DateInfoList(days, holidays);
	}			
	
	private List<DateInfo> getRawDateInfoList(LocalDate fromDate, LocalDate toDate) {
		if (fromDate.isAfter(toDate)) 
			throw new IllegalArgumentException("종료일자보다 시작일자가 큽니다.");
		
		List<DateInfo> list = new ArrayList<>(366);			
		
		while (fromDate.isBefore(toDate) || fromDate.isEqual(toDate)) {
			list.add(new DateInfo(fromDate));			
			fromDate = fromDate.plusDays(1);
		}
		
		return list;
	}
	
	private List<Holiday> getHolidayList(LocalDate fromDate, LocalDate toDate) {
		return holidayRepository.getHoliday(fromDate, toDate);
	}	
		
	public void ss(LocalDate from, LocalDate to) {
		List<LocalDate> days = this.toLocalDateList(from, to);
		List<Holiday> holidays = this.getHolidayList(from, to);
		
		for (LocalDate day: days) {
		}
	}
	
	
	public static List<LocalDate> toLocalDateList(LocalDate start, LocalDate end) {		  
		final long days = start.until(end, ChronoUnit.DAYS);

		return LongStream.rangeClosed(0, days)
					     .mapToObj(start::plusDays)
					     .collect(Collectors.toList());
	}
	
}
