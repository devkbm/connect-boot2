package com.like.hrm.duty.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.like.hrm.duty.domain.model.enums.YearType;

import lombok.Getter;

@Getter
@Entity
public class DutyCodeLimitRule {

	private Long id;

	private String fromYear;
	
	private LocalDate fromDate;
	
	private String toYear;
	
	private LocalDate toDate;
	
	private String invalidMessage;
	
	private String comment;
	
	public LocalDate getFrom() {
		if (this.fromYear.equals("PREV")) {
			return LocalDate.of(LocalDate.now().getYear() - 1, fromDate.getMonth(), fromDate.getDayOfMonth());
		} 
		
		return LocalDate.of(LocalDate.now().getYear(), fromDate.getMonth(), fromDate.getDayOfMonth());				
	}
	
	public LocalDate getTo() {
		if (this.toYear.equals("PREV")) {
			return LocalDate.of(LocalDate.now().getYear() - 1, toDate.getMonth(), toDate.getDayOfMonth());
		} 
		
		return LocalDate.of(LocalDate.now().getYear(), toDate.getMonth(), toDate.getDayOfMonth());
	}
}
