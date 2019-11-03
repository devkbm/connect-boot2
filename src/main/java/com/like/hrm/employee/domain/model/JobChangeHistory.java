package com.like.hrm.employee.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
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

import com.like.common.domain.AuditEntity;

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
	
	/**
	 * 시작일
	 */
	@Column(name="FROM_DT")
	private LocalDate fromDate;
	
	/**
	 * 종료일
	 */
	@Column(name="TO_DT")
	private LocalDate toDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;

	@Builder
	public JobChangeHistory(Employee employee, String jobType, String jobCode, LocalDate fromDate, LocalDate toDate) {
		this.employee 	= employee;
		this.jobType 	= jobType;
		this.jobCode 	= jobCode;		
		this.fromDate 	= fromDate;
		this.toDate 	= toDate;				
	}		
	
	public boolean isEnabled(LocalDate date) {
		return  ( date.isAfter(fromDate) || date.isEqual(fromDate) ) 
			 && ( date.isBefore(toDate) || date.isEqual(toDate) ) ? true : false;		
	}
	
	public void terminateHistory(LocalDate date) {
		if (date.isAfter(this.fromDate)) {
			this.toDate = date;
		} else {
			this.toDate = this.fromDate;
		}
	}
	
	public boolean equalJobType(String jobType) {
		return this.jobType.equals(jobType) ? true : false;
	}
	
	public boolean equalJobCode(String jobCode) {
		return this.jobCode.equals(jobCode) ? true : false;
	}
	
}
