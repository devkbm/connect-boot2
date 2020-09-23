package com.like.hrm.duty.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;
import com.like.hrm.duty.domain.model.enums.YearType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice.This;

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
	
	@Column(name="FROM_DT", nullable = false)
	private LocalDate fromDate;
	
	@Column(name="TO_YEAR_TYPE", nullable = false)
	private String toYear;
	
	@Column(name="TO_DT", nullable = false)
	private LocalDate toDate;
	
	@Column(name="CNT", nullable = false)
	private Long count;
	
	@Column(name="INVALID_MSG", nullable = false)
	private String invalidMessage;
	
	@Column(name="CMT", nullable = true)
	private String comment;
	
	@Builder
	public DutyApplicationInputLimitRule(String fromYear
										,LocalDate fromDate
										,String toYear
										,LocalDate toDate
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
		if (this.fromYear.equals("PREV")) {
			return LocalDate.of(LocalDate.now().getYear() - 1, fromDate.getMonth(), fromDate.getDayOfMonth());
		} 
		
		return LocalDate.of(LocalDate.now().getYear(), fromDate.getMonth(), fromDate.getDayOfMonth());				
	}
	
	public LocalDate getTo() {
		if (this.toYear.equals("PREV")) {
			return LocalDate.of(LocalDate.now().getYear() - 1, toDate.getMonth(), toDate.getDayOfMonth());
		} 
		
		return LocalDate.of(LocalDate.now().getYear(), toDate.getMonth(), toDate.getDayOfMonth());
	}
}
