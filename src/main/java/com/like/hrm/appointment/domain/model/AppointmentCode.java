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
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>발령 코드 기준 정보</p> 
 * [상세] <br/>
 * 1. 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "HRMAPPOINTMENTCODE")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentCode extends AuditEntity implements Serializable {
	 
	private static final long serialVersionUID = -2792716645396219283L;
	
	@Id	
	@Column(name="appointment_code")
	String code;
	
	@Column(name="appointment_code_name")
	String codeName;
		
	@Column(name="use_yn")
	boolean useYn = true;
			
	@Column(name="prt_seq")
	Integer sequence;
	
	@OneToMany(mappedBy = "appointmentCode", cascade = CascadeType.ALL, orphanRemoval = true )
	@MapKeyColumn(name="TYPE_ID", insertable = false, updatable = false, nullable = false)
	private Map<String, AppointmentCodeDetail> codeDetails = new HashMap<String, AppointmentCodeDetail>();

	/**
	 * @param code
	 * @param codeName
	 * @param useYn
	 * @param sequence
	 * @param codeDetails
	 */
	public AppointmentCode(String code
						  ,String codeName
						  ,boolean useYn
						  ,Integer sequence
						  ,Map<String, AppointmentCodeDetail> codeDetails) {		
		this.code = code;
		this.codeName = codeName;
		this.useYn = useYn;
		this.sequence = sequence;
		this.codeDetails = codeDetails;
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
