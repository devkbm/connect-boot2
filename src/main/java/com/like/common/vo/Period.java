package com.like.common.vo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class Period {

	@Column(name="FROM_DT")
	private LocalDate fromDate;
	
	@Column(name="TO_DT")
	private LocalDate toDate;
	
	public Period(LocalDate fromDate
				 ,LocalDate toDate) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		
		if (!isValid())
			throw new IllegalArgumentException(
					String.format("시작일자[%s]가 종료일자[%s]보다 클 수 없습니다."
								 ,fromDate.toString()
								 ,toDate.toString()));
		
	}
	
	private boolean isValid() {		
		return fromDate.isAfter(toDate) ? false : true;
	}
	
	public static void main(String[] args) {
		Period p1 = new Period(LocalDate.of(1991, 1, 1), LocalDate.of(1991, 1, 2));
		Period p2 = new Period(LocalDate.of(1990, 1, 1), LocalDate.of(1991, 1, 2));
		
		System.out.println(p1.toString());
		System.out.println(p2.toString());
	}
}
