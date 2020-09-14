package com.like.hrm.duty.domain.model.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class FamilyEvent {

	/**
	 * 경조사 발생일자
	 */
	@Column(name="FAMILY_EVENT_DT")
	private LocalDate familyEventDay;
	
	/**
	 * 경조사 지급금액
	 */
	@Column(name="FAMILY_EVENT_AMT")
	private Long familyEventAmt;
	
	public FamilyEvent(LocalDate familyEventDay
					  ,Long familyEventAmt) {
		this.familyEventDay = familyEventDay;
		this.familyEventAmt = familyEventAmt;
	}
}
