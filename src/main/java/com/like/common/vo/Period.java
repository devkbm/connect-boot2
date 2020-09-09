package com.like.common.vo;

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
	
	public Period(LocalDateTime from
				 ,LocalDateTime to) {
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
		Period p1 = new Period(LocalDateTime.of(1991, 1, 1, 1, 1 ,0), LocalDateTime.of(1991, 1, 2, 1, 1 ,0));
		Period p2 = new Period(LocalDateTime.of(1990, 1, 1, 1, 1 ,0), LocalDateTime.of(1991, 1, 2, 1, 1 ,0));
		
		System.out.println(p1.toString());
		System.out.println(p2.toString());
	}
}
