package com.like.hrm.duty.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

import lombok.Getter;

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
	
	@Column(name="LIMIT_ID", nullable = false)
	private Long dutyApplicationInputLimitId;
	
	public DutyCodeRule(DutyCode dutyCode
					   ,Long dutyApplicationInputLimitId) {
		this.dutyCode = dutyCode;
		this.dutyApplicationInputLimitId = dutyApplicationInputLimitId;
	}
		
}
