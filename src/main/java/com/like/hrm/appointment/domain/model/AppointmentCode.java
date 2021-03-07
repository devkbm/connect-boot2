package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>발령 코드 기준 정보</p> 
 * [상세] <br/>
 * 1.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "HRMAPPOINTMENTCODE")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentCode extends AuditEntity implements Serializable {
	 
	private static final long serialVersionUID = -2792716645396219283L;
	
	@Id	
	@Column(name="APPOINTMENT_CODE")
	String code;
	
	@Column(name="APPOINTMENT_CODE_NAME")
	String codeName;
		
	@Column(name="USE_YN")
	boolean useYn = true;
	
	/**
	 * 종료일자 필수 여부
	 */
	@Column(name="END_DATE_YN")
	boolean endDateYn = false;
	
	@Column(name="PRT_SEQ")
	Integer sequence;
		
	@Column(name="CMT")
	String comment;	
	
	@OneToMany(mappedBy = "appointmentCode", cascade = CascadeType.ALL, orphanRemoval = true )
	@MapKeyColumn(name="TYPE_ID", insertable = false, updatable = false, nullable = false)
	private Map<String, AppointmentCodeDetail> codeDetails = new HashMap<String, AppointmentCodeDetail>();
	
	public AppointmentCode(String code
						  ,String codeName
						  ,boolean useYn
						  ,boolean endDateYn
						  ,Integer sequence
						  ,String comment
						  ,Map<String, AppointmentCodeDetail> codeDetails) {		
		this.code 		= code;
		this.codeName 	= codeName;
		this.useYn 		= useYn;
		this.endDateYn 	= endDateYn;
		this.sequence 	= sequence;
		this.comment	= comment;
		this.codeDetails = codeDetails;
	}		
	
	public void changeInfo(String codeName
						  ,boolean useYn
						  ,boolean endDateYn
						  ,Integer sequence
						  ,String comment) {
		this.codeName 	= codeName;
		this.useYn 		= useYn;
		this.endDateYn 	= endDateYn;
		this.sequence 	= sequence;
		this.comment	= comment;
	}
	
	public AppointmentCodeDetail getCodeDetail(String pk) {				
		return this.codeDetails.get(pk);
	}
	
	public void addAppointmentCodeDetail(AppointmentCodeDetail detail) {
		if (this.codeDetails == null)
			this.codeDetails = new HashMap<String, AppointmentCodeDetail>();
							
		this.codeDetails.put(detail.getId(), detail);		
	}
	
	public void deleteAppointmentCodeDetail(String pk) {		
		if (!this.codeDetails.containsKey(pk)) {
			throw new EntityNotFoundException(pk+ "가 존재하지 않습니다.");
		} 
		
		this.codeDetails.remove(pk);
	}
	
	
	
}
