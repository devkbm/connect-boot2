package com.like.holiday.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DateInfoList {

	private Map<LocalDate, DateInfo> dates;
	
	public DateInfoList(List<DateInfo> dates) {
		this.dates = dates.stream().collect(Collectors.toMap(DateInfo::getDate, dateInfo -> dateInfo));
	}
	
	public DateInfoList(List<DateInfo> dates, List<Holiday> holidayList) {
		this.dates = dates.stream().collect(Collectors.toMap(DateInfo::getDate, dateInfo -> dateInfo));
		this.setHolidayList(holidayList);
	}
	
	public DateInfo getDate(LocalDate date) {												
		return this.dates.get(date);
	}
	
	public List<DateInfo> getDates() {	
		List<DateInfo> list = new ArrayList<>(this.dates.values());
		Collections.sort(list, (e1, e2) -> e1.getDate().compareTo(e2.getDate()));
		return list;
	}
		
	public int size() {
		return this.dates.size();
	}
	
	private void setHolidayList(List<Holiday> holidayList) {
		for (Holiday holiday: holidayList) {								
			this.dates.get(holiday.getDate()).setHoliday(holiday);
		}
	}	
}
