package com.like.hrm.anualleave.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * 직원 연차 클래스
 *
 */
public class AnualLeave {

	// 귀속년도
	Integer yyyy;
		
	// 사원번호
	String empId;
			
	// 연차기준일(입사일)
	LocalDate base;
	
	// 사용 시작일자
	LocalDate from;
	
	// 사용 종료일자
	LocalDate to;
	
	// 발생갯수
	float cnt;
	
	// 가산갯수
	float add_cnt;
	
	// 사용갯수
	float use_cnt;
	
	// 총근무일수
	long total_work_days;
	
	// 제외근무일수
	long except_days;

	// 1년 미만 여부
	Boolean isIntraAnual;
	
	// 비고
	String comment;
	
	public AnualLeave(Integer yyyy
					 ,String empId
					 ,LocalDate base 					 
					 ,LocalDate from
					 ,LocalDate to) {
		this.yyyy = yyyy;
		this.empId = empId;
		this.base = base;		
		this.from = from;
		this.to = to;			
	}
			
	/**
	 * 출근율을 구한다.
	 * @return 출근율
	 */
	public double getWorkRate() {		
		return (double)(total_work_days - except_days) / total_work_days * 100;
	}	
	
	public void calc(LocalDate toDate) {
		
		// 1. 1년 미만 근로자 여부 설정
		this.setIntraAnual(toDate);
		
		// 2. 총근무일수를 계산
		this.calcTotalWorkDays(toDate);
		
		// 출근율 80% 미만이거나, 1년이하 근무자 월만근갯수 휴가 발생
		if ( this.getWorkRate() < 80 || this.isIntraAnual ) {
			this.cnt = ChronoUnit.MONTHS.between(this.from, toDate);
		} else {
		// 발생연차 + 누진 연차(2년에 1개 추가 총 25개까지)
			this.cnt = 15;
			System.out.println("누진" + this.getWorkYears(toDate));
			if (this.getWorkYears(toDate) > 2) {
				this.add_cnt = (int)Math.floor(this.getWorkYears(toDate) / 2);
			}
				
		}
	}
	
	/**
	 * 기준일로부터 1년 미만 여부를 설정한다.
	 * @param toDate
	 */	
	private void setIntraAnual(LocalDate toDate) {		                   
		if (this.getWorkYears(toDate) < 1)
			this.isIntraAnual = true;
		else 
			this.isIntraAnual = false;			
	}
			
	/**
	 * 총근무일수를 계산한다.
	 * @param date 
	 */
	private void calcTotalWorkDays(LocalDate toDate) {
		LocalDateTime from = this.from.atStartOfDay();
		LocalDateTime to = toDate.atStartOfDay();
		
		this.total_work_days = Duration.between(from, to).toDays();
	}
	
	private int getWorkYears(LocalDate toDate) {
		return Period.between(this.base, toDate).getYears();
	}
	
	public static void main(String[] args) {
		AnualLeave a = new AnualLeave(2021
									 ,"111"
									 ,LocalDate.of(2020, 1, 1)
									 ,LocalDate.of(2026, 1, 1)
									 ,LocalDate.of(2026, 12, 31));
		a.except_days = 0;
		a.calc(LocalDate.of(2026, 02, 01));
				
		System.out.println(a.isIntraAnual);
		System.out.println(a.total_work_days);
		System.out.println(a.except_days);
		System.out.println(a.cnt + ":" + a.add_cnt);		
		System.out.println(a.getWorkRate());		
		
	}

}
