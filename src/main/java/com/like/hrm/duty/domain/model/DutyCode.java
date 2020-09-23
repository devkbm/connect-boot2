package com.like.hrm.duty.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@Column(name="DUTY_GROUP", nullable = false)
	private String dutyGroup;
		
	// 경조비 대상 여부
	@Column(name="FAMILY_EVENT_YN", nullable = false)
	private Boolean isFamilyEvent;
	
	// 경조비 지급 금액
	@Column(name="FAMILY_EVENT_AMT", nullable = true)
	private Long familyEventAmt;		
	
	@Column(name = "CMT", nullable = true)
	private String comment;
	
	@OneToMany(mappedBy = "article", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<DutyCodeRule> dutyCodeRule;
	
	public void modifyEntity(String dutyName
							,Boolean enabled
							,String dutyGroup
							,Boolean isFamilyEvent
							,Long familyEventAmt
							,String comment) {
		this.dutyName = dutyName;
		this.enabled = enabled;
		this.dutyGroup = dutyGroup;
		this.isFamilyEvent = isFamilyEvent;
		this.familyEventAmt = familyEventAmt;
		this.comment = comment;
	}
	
	public boolean containDutyApplicationInputLimit(Long id) {
		return this.dutyCodeRule.stream()
								.filter(e -> e.getDutyApplicationInputLimitId().equals(id))
								.count() > 1 ? true : false;
	}
}
