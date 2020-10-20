package com.like.holiday.domain.model;

import java.time.LocalDate;
import java.util.List;

public class DateInfoList {

	private List<DateInfo> dates;
	
	public DateInfoList(List<DateInfo> dates) {
		this.dates = dates;
	}
	
	public DateInfo getDate(LocalDate date) {
		DateInfo rtn = null;
		
		for (DateInfo dateInfo : this.dates) {
			if (date.equals(dateInfo.getDate()))
				rtn = dateInfo;
		}
			
		return rtn;
	}
	
	public List<DateInfo> getDates() {
		return this.dates;
	}
	
	public int size() {
		return this.dates.size();
	}
}
