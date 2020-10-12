package com.like.hrm.duty.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMDUTYAPPLICATIONLIMIT")
public class DutyApplicationInputLimitRule extends AuditEntity {

	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LIMIT_ID", nullable = false)
	private Long id;

	@Column(name="FROM_YEAR_TYPE", nullable = false)
	private String fromYear;
	
	@Column(name="FROM_MMDD", nullable = false)
	private String fromDate;
	
	@Column(name="TO_YEAR_TYPE", nullable = false)
	private String toYear;
	
	@Column(name="TO_MMDD", nullable = false)
	private String toDate;
	
	@Column(name="CNT", nullable = false)
	private Long count;
	
	@Column(name="INVALID_MSG", nullable = false)
	private String invalidMessage;
	
	@Column(name="CMT", nullable = true)
	private String comment;
	
	@Builder
	public DutyApplicationInputLimitRule(String fromYear
										,String fromDate
										,String toYear
										,String toDate
										,Long count
										,String invalidMessage
										,String comment) {	
		this.fromYear = fromYear;
		this.fromDate = fromDate;
		this.toYear = toYear;
		this.toDate = toDate;
		this.count = count;
		this.invalidMessage = invalidMessage;
		this.comment = comment;
	}
	
	public void modifyEntity(String fromYear
							,String fromDate
							,String toYear
							,String toDate
							,Long count
							,String invalidMessage
							,String comment) {
		this.fromYear = fromYear;
		this.fromDate = fromDate;
		this.toYear = toYear;
		this.toDate = toDate;
		this.count = count;
		this.invalidMessage = invalidMessage;
		this.comment = comment;
	}
	
	public LocalDate getFrom() {
		int month = Integer.parseInt(fromDate.substring(0, 2));
		int day = Integer.parseInt(fromDate.substring(2, 2));
		
		if (this.fromYear.equals("PREV")) {
			return LocalDate.of(LocalDate.now().getYear() - 1, month, day);
		} 
		
		return LocalDate.of(LocalDate.now().getYear(), month, day);			
	}
	
	public LocalDate getTo() {
		/*if (this.toYear.equals("PREV")) {
			return LocalDate.of(LocalDate.now().getYear() - 1, toDate.getMonth(), toDate.getDayOfMonth());
		} 
		
		return LocalDate.of(LocalDate.now().getYear(), toDate.getMonth(), toDate.getDayOfMonth());*/
		return null;
	}
}
