package com.like.core.vo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class Period {

	@Column(name="FROM_DT")
	private LocalDateTime from;
	
	@Column(name="TO_DT")
	private LocalDateTime to;
	
	public Period(LocalDateTime from, LocalDateTime to) {
		this.from = from;
		this.to = to;
		
		if (!isValid(from, to))
			throw newException();		
	}	
	
	/**
	 * 비교대상기간이 from ~ to 사이에 포함되어있는지 여부를 리턴한다.
	 * @param targetFrom 비교대상시작일시
	 * @param targetTo	 비교대상종료일시
	 * @return 포함여부
	 */
	public boolean isBetween(LocalDateTime targetFrom, LocalDateTime targetTo) {
		
		if (targetFrom == null || targetTo == null)
			throw new IllegalArgumentException("null 객체를 허용하지 않습니다.");		
		
		if (!isValid(targetFrom, targetTo))
			throw newException();
		
		// 1. 비교대상 시작일시가 from ~ to 사이에 포함될 경우
		if ( isBetween(targetFrom) )
			return true;
		
		// 2. 비교대상 종료일시가 from ~ to 사이에 포함될 경우
		if ( isBetween(targetTo) )
			return true;
		
		// 3. 비교대상 기간이 from ~ to 사이인 경우  
		if ( this.from.isBefore(targetFrom) && this.to.isAfter(targetTo) )
			return true;
		
		return false;
	}
	
	/**
	 * 비교대상기간이 from ~ to 사이에 포함되어있는지 여부를 리턴한다.
	 * @param period 비교대상기간
	 * @return 포함여부
	 */
	public boolean isBetween(Period period) {		
		return isBetween(period.getFrom(), period.getTo());
	}
	
	/**
	 * 특정일시가 from ~ to 사이에 포함되어있는지 여부를 리턴한다.
	 * @param dt 특정일시
	 * @return 포함여부
	 */
	public boolean isBetween(LocalDateTime dt) {
		if ( (this.from.isBefore(dt) || this.from.isEqual(dt)) && 
			 (this.to.isAfter(dt) || this.to.isEqual(dt)) )
			return true;
		
		return false;
	}
	
	private boolean isValid(LocalDateTime from, LocalDateTime to) {		
		return from.isAfter(to) ? false : true;
	}
	
	private IllegalArgumentException newException() {
		return new IllegalArgumentException(
				String.format("시작일시[%s]가 종료일시[%s]보다 클 수 없습니다.",from.toString(),to.toString()));
	}
	
	public static void main(String[] args) {
		Period p1 = new Period(LocalDateTime.of(1991, 1, 1, 1, 1 ,0), LocalDateTime.of(1991, 1, 2, 1, 1 ,0));
		Period p2 = new Period(LocalDateTime.of(1990, 1, 1, 1, 1 ,0), LocalDateTime.of(1991, 1, 2, 1, 1 ,0));
		
		System.out.println(p1.toString());
		System.out.println(p2.toString());
		
		System.out.println(p1.isBetween(p2));
	}
}
