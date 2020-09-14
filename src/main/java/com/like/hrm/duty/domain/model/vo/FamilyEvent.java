package com.like.hrm.duty.domain.model.vo;

import java.time.LocalDate;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FamilyEvent {

	/**
	 * 경조사 일자
	 */
	private LocalDate familyEventDay;
	
	/**
	 * 경조사 지원금액
	 */
	private Long familyEventAmt;
	
	public FamilyEvent(LocalDate familyEventDay
					  ,Long familyEventAmt) {
		this.familyEventDay = familyEventDay;
		this.familyEventAmt = familyEventAmt;
	}
}
