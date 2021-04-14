package com.like.hrm.duty.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.like.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMDUTYAPPLICATIONDATE")
public class DutyApplicationDate extends AuditEntity {

	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
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
