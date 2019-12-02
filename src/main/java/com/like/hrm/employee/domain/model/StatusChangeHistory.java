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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"employee"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMEMPSTATUSHISTORY")
@EntityListeners(AuditingEntityListener.class)
public class StatusChangeHistory extends AuditEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
			
	@Column(name="APPOINTMENT_CODE")
	private String appointmentCode;
		
	@Column(name="STATUS_CODE")
	private String statusCode;
	
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

	/**
	 * @param employee
	 * @param appointmentCode
	 * @param statusCode
	 * @param fromDate
	 * @param toDate
	 */
	public StatusChangeHistory(Employee employee
							  ,String appointmentCode
							  ,String statusCode
							  ,LocalDate fromDate
							  ,LocalDate toDate) {		
		this.employee = employee;
		this.appointmentCode = appointmentCode;
		this.statusCode = statusCode;
		this.fromDate = fromDate;
		this.toDate = toDate;	
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
	
	
}
