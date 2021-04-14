package com.like.hrm.employee.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.core.domain.AuditEntity;
import com.like.core.vo.DatePeriod;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>직위, 직급등 이력 관리 클래스</p>
 * 
 * Index : EMP_ID, JOB_TYPE, JOB_CODE <br>
 * [상세] <br>
 * 1. <br>
 * 2. <br>
 * @author 김병민
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"employee"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMEMPJOBHISTORY")
@EntityListeners(AuditingEntityListener.class)
public class JobChangeHistory extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -1926241614174202250L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
			
	@Column(name="JOB_TYPE")
	private String jobType;
	
	@Column(name="JOB_CODE")
	private String jobCode;	
	
	/*
	@Column(name="FROM_DT")
	private LocalDate fromDate;
		
	@Column(name="TO_DT")
	private LocalDate toDate;
	*/
	
	@Embedded
	private DatePeriod period;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;

	@Builder
	public JobChangeHistory(Employee employee, String jobType, String jobCode, DatePeriod period) {
		this.employee 	= employee;
		this.jobType 	= jobType;
		this.jobCode 	= jobCode;	
		this.period 	= period;				
	}		
	
	public boolean isEnabled(LocalDate date) {
		return  ( date.isAfter(this.period.getFrom()) || date.isEqual(this.period.getFrom()) ) 
			 && ( date.isBefore(this.period.getTo()) || date.isEqual(this.period.getTo()) ) ? true : false;		
	}
	
	public void expire(LocalDate date) {
		if (date.isAfter(this.period.getFrom())) {
			this.period = new DatePeriod(this.period.getFrom(), date);			
		} else {
			this.period = new DatePeriod(this.period.getFrom(), this.period.getFrom());								
		}
	}
	
	public boolean equal(String jobType, String jobCode) {
		return this.jobType.equals(jobType) && this.jobCode.equals(jobCode) ? true : false;
	}
	
	public boolean equalJobType(String jobType) {
		return this.jobType.equals(jobType) ? true : false;
	}
	
	public boolean equalJobCode(String jobCode) {
		return this.jobCode.equals(jobCode) ? true : false;
	}
	
}
