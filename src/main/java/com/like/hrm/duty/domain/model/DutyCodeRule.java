package com.like.hrm.duty.domain.model;

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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMDUTYCODERULE")
public class DutyCodeRule extends AuditEntity {

	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RULE_ID", nullable = false)
	private Long id;
			
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DUTY_CODE", nullable=false)
	private DutyCode dutyCode;
	
	@Column(name="FK_LIMIT_ID", nullable = false)
	private Long dutyApplicationInputLimitId;
	
	public DutyCodeRule(DutyCode dutyCode
					   ,Long dutyApplicationInputLimitId) {
		this.dutyCode = dutyCode;
		this.dutyApplicationInputLimitId = dutyApplicationInputLimitId;
	}
		
}
