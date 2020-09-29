package com.like.holiday.domain.model;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

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
		return date;
	}

	public Holiday getHoliday() {
		return holiday;
	}

	public String getDayOfWeek() {
		return this.date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.getDefault());
	}

	public void setHoliday(Holiday holiday) {
		this.holiday = holiday;
	}
	
	public static void main(String[] args) {
		DateInfo date = new DateInfo(LocalDate.now());
		
		System.out.println(date.getDayOfWeek());
	}
}
