package com.like.hrm.duty.domain.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.like.common.domain.AuditEntity;
import com.like.common.vo.Period;
import com.like.hrm.duty.domain.model.vo.FamilyEvent;

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
@Table(name = "HRMDUTYAPPLICATION")
public class DutyApplication extends AuditEntity {
	
	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="DUTY_ID", nullable = false)
	private Long dutyId;
	
	@Column(name="EMP_ID", nullable = false)
	private String employeeId;
	
	@Column(name="DUTY_CODE", nullable = false)
	private String dutyCode;
	
	@Column(name="DUTY_REASON", nullable = false)
	private String dutyReason;	
		
	@AttributeOverrides({
		@AttributeOverride(name = "from", column = @Column(name = "DUTY_START_DT")),
		@AttributeOverride(name = "to", column = @Column(name = "DUTY_END_DT"))
	})
	Period period;
	
	@Embedded
	FamilyEvent familyEvent;
	
	@Transient
	private List<DutyApplicationAttachedFile> fileList;
	
	public DutyApplication(String employeeId
						  ,String dutyCode
						  ,String dutyReason
						  ,Period period
						  ,FamilyEvent familyEvent) {
		this.employeeId = employeeId;
		this.dutyCode = dutyCode;
		this.dutyReason = dutyReason;
		this.period = period;
		this.familyEvent = familyEvent;
	}	
	
	public void modifyEntity(String dutyCode
							,String dutyReason
							,Period period) {
		this.dutyCode = dutyCode;
		this.dutyReason = dutyReason;
		this.period = period;
	}
	
	public void addFile(DutyApplicationAttachedFile file) {
		this.fileList.add(file);
	}
	
}