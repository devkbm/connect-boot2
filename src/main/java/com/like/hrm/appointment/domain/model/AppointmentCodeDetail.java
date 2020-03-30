package com.like.hrm.appointment.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>발령 코드 상세 정보</p> 
 * [상세] <br/>
 * 1. 식별자 : 발령코드 + 변경유형 + 변경유형상세
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"appointmentCode"})
@Getter
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Entity
@Table(name = "HRMAPPOINTMENTCODEDETAIL")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentCodeDetail extends AuditEntity implements Serializable {
		
	private static final long serialVersionUID = -9205194638867469788L;

	/**
	 * 코드구성 = 발령코드 + 변경유형 + 변경유형상세
	 */
	@Id	
	@Column(name="TYPE_ID")
	private String id;	
	
	// 인사, 부서, 근무상태	
	@Column(name="CHANGE_TYPE")
	@Enumerated(EnumType.STRING)
	HrmTypeEnum changeType;
			
	// 소속부서, 직위 등
	@Column(name="CHANGE_TYPE_DETAIL")
	private String changeTypeDetail;
		
	@Column(name="PRT_SEQ")
	private Integer sequence;									
	
	@ManyToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name="appointment_code", nullable=false, updatable=false)
	private AppointmentCode appointmentCode;

	/**
	 * @param appointmentCode
	 * @param changeType
	 * @param changeTypeDetail
	 * @param sequence
	 */
	public AppointmentCodeDetail(AppointmentCode appointmentCode
								,HrmTypeEnum changeType
								,String changeTypeDetail
								,Integer sequence) {
		this.id = appointmentCode.code + changeType.toString() + changeTypeDetail;
		this.appointmentCode = appointmentCode;
		this.changeType = changeType;
		this.changeTypeDetail = changeTypeDetail;
		this.sequence = sequence;		
	}	
	
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
}
