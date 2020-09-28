package com.like.holiday.domain.model;

import java.time.LocalDate;

public class DateInfo {

	private LocalDate date;
	
	private Holiday holiday;
	
	public DateInfo(LocalDate date) {
		this.date = date;
	}
	
	public DateInfo(LocalDate date
				   ,Holiday holiday) {
		this.date = date;
		this.holiday = holiday;
	}
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}
}
