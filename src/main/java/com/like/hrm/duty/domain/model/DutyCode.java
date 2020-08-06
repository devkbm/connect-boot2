package com.like.hrm.duty.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMDUTYCODE")
public class DutyCode extends AuditEntity {

	@Id		
	@Column(name="DUTY_CODE")
	private String dutyCode;
	
	@Column(name="DUTY_NAME")
	private String dutyName;
	
	@Column(name="ENABLE_YN")
	private Boolean enabled;
	
}
