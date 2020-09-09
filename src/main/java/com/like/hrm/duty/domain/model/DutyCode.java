package com.like.hrm.duty.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMDUTYCODE")
public class DutyCode extends AuditEntity {

	@Id		
	@Column(name="DUTY_CODE", nullable = false)
	private String dutyCode;
	
	@Column(name="DUTY_NAME", nullable = false)
	private String dutyName;
	
	@Column(name="ENABLE_YN", nullable = false)
	private Boolean enabled;
	
	@Column(name="DUTY_GROUP", nullable = true)
	private String dutyGroup;
		
	public void modifyEntity(String dutyName
							,Boolean enabled
							,String dutyGroup) {
		
	}
}
