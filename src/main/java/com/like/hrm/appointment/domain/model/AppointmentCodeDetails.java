package com.like.hrm.appointment.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>발령 코드 기준 정보</p> 
 * [상세] <br/>
 * 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"appointmentCode"})
@Getter
@Entity
@Table(name = "HRMAPPOINMENTCODEDETAILS")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentCodeDetails extends AuditEntity implements Serializable {
		
	private static final long serialVersionUID = -9205194638867469788L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_CODE_DETAIL")
	private Long pkCodeDetails;	
	
	// 인사, 부서, 근무상태
	@Column(name="CHANGE_TYPE")
	private String changeType;
			
	// 소속부서, 직위 등
	@Column(name="CHANGE_TYPE_DETAIL")
	private String changeTypeDetail;
		
	@Column(name="prt_seq")
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
	public AppointmentCodeDetails(AppointmentCode appointmentCode
								 ,String changeType
								 ,String changeTypeDetail
								 ,Integer sequence) {
		this.appointmentCode = appointmentCode;
		this.changeType = changeType;
		this.changeTypeDetail = changeTypeDetail;
		this.sequence = sequence;		
	}	
	
	
}
