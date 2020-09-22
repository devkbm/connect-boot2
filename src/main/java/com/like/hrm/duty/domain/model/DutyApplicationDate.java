package com.like.hrm.duty.domain.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

import lombok.Getter;

@Getter
@Entity
@Table(name = "HRMDUTYAPPLICATIONDATE")
public class DutyApplicationDate extends AuditEntity {

	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	private Long id;
	
	@JoinColumn(name = "FK_DUTY_ID", nullable=false, updatable=false)
	private DutyApplication dutyApplication;
	
	@Column(name="DUTY_DT", nullable = false)
	private LocalDate date;
	
	public DutyApplicationDate(DutyApplication dutyApplication
							  ,LocalDate date) {
		this.dutyApplication = dutyApplication;
		this.date = date;
	}
	
}
