package com.like.holiday.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class Holiday {

	private LocalDate date;
	
	private String holidayName;
	
	private String comment;		
}
