package com.like.common.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class DatePeriod {

	@Column(name="FROM_DT")
	private LocalDate from;
	
	@Column(name="TO_DT")
	private LocalDate to;
	
	public DatePeriod(LocalDate from
				 	 ,LocalDate to) {
		this.from = from;
		this.to = to;
		
		if (!isValid())
			throw new IllegalArgumentException(
					String.format("시작일시[%s]가 종료일시[%s]보다 클 수 없습니다."
								 ,from.toString()
								 ,to.toString()));
		
	}
	
	private boolean isValid() {		
		return from.isAfter(to) ? false : true;
	}
	
	public static void main(String[] args) {
		DatePeriod p1 = new DatePeriod(LocalDate.of(1991, 1, 1), LocalDate.of(1991, 1, 2));
		DatePeriod p2 = new DatePeriod(LocalDate.of(1990, 1, 1), LocalDate.of(1991, 1, 2));
		
		System.out.println(p1.toString());
		System.out.println(p2.toString());
	}
}
