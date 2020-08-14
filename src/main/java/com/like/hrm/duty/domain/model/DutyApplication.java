package com.like.hrm.duty.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMDUTYAPPLICATION")
public class DutyApplication extends AuditEntity {
	
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DUTY_ID")
	private Long dutyId;
	
	@Column(name="EMP_ID")
	private String employeeId;
	
	@Column(name="DUTY_CODE")
	private String dutyCode;
	
	@Column(name="DUTY_REASON")
	private String dutyReason;
	
	@Column(name="DUTY_START_DT")
	private LocalDateTime dutyStartDateTime;
	
	@Column(name="DUTY_END_DT")
	private LocalDateTime dutyEndDateTime;
	
	@Transient
	private List<DutyApplicationAttachedFile> fileList;
	
	public DutyApplication(String employeeId
			, String dutyCode
			, String dutyReason
			, LocalDateTime dutyStartDateTime
			,LocalDateTime dutyEndDateTime) {
		this.employeeId = employeeId;
		this.dutyCode = dutyCode;
		this.dutyReason = dutyReason;
		this.dutyStartDateTime = dutyStartDateTime;
		this.dutyEndDateTime = dutyEndDateTime;
	}	
	
	public void addFile(DutyApplicationAttachedFile file) {
		this.fileList.add(file);
	}
	
}
