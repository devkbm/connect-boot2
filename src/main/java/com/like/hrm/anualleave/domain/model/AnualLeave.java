package com.like.hrm.anualleave.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import javax.persistence.EmbeddedId;

import lombok.Getter;

/**
 * <p>직원 연차 클래스</p>
 * 
 * 연차 계산 로직 <br>
 *   - 1. 1년 초과 근무자는 15개 연차 발생. <br>
 *   - 2. 1년 미만 또는 출근율 80% 미만 근무자는 월만근갯수당 1개씩 연차발생 <br>
 *   - 3. 1년 초과후 2년마다 1개씩 누진 연차 발생 <br>
 * 제약조건 <br>
 *   - 1. 연차발생갯수는 25개를 초과할 수 없다. <br>
 */
@Getter
public class AnualLeave {
	
	@EmbeddedId
	AnualLeaveId id;
			
	// 연차기준일(입사일)
	LocalDate base;
	
	// 사용 시작일자
	LocalDate from;
	
	// 사용 종료일자
	LocalDate to;
	
	// 발생갯수
	double cnt;
	
	// 가산갯수
	double add_cnt;
	
	// 사용갯수
	double use_cnt;
	
	// 총근무일수
	long total_work_days;
	
	// 제외근무일수
	long except_days;

	// 1년 미만 여부
	Boolean isIntraAnual;
	
	// 비고
	String comment;
	
	public AnualLeave(AnualLeaveId id
					 ,LocalDate base 					 
					 ,LocalDate from
					 ,LocalDate to) {
		this.id = id;
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
		
		// 3. 출근율 80% 미만이거나, 1년이하 근무자 월만근갯수 휴가 발생
		if ( this.getWorkRate() < 80 || this.isIntraAnual ) {
			this.cnt = ChronoUnit.MONTHS.between(this.from, toDate);
		} else {
		// 발생연차 + 누진 연차(2년에 1개 추가 총 25개까지)
			this.cnt = 15;
			System.out.println("누진" + this.getWorkYears(toDate));
			if (this.getWorkYears(toDate) > 2) {
				this.add_cnt = Math.floor(this.getWorkYears(toDate) / 2);
			}
				
		}
		
		// 4. 총 갯수가 25가 넘으면 25로 설정
		if (this.cnt + this.add_cnt > 25) {
			this.cnt = 15;
			this.add_cnt = 10;
		}
	}
	
	public double getTotalCount() {
		return this.cnt + this.add_cnt;
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
		AnualLeave a = new AnualLeave(new AnualLeaveId(2021, "1111")
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
