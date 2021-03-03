package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
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
 * <p>발령대장 정보</p> 
 * [상세] <br/>
 * 1. <br/>
 * [제약사항] <br/>
 * 1. <br/>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Entity
@Table(name = "HRMAPPOINTMENTLEDGER")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentRegister extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -5893205308411172278L;

	/**
	 * 원장 번호
	 */
	@Id	
	@Column(name="LEDGER_ID")
	String id;
		
	/**
	 * 발령 유형(정기, 임의)
	 */
	@Column(name="APPOINTMENT_TYPE")
	String appointmentType;
	
	/**
	 * 등록일
	 */
	@Column(name="RGST_DT")
	LocalDate registrationDate;
		
	/**
	 * 비고
	 */
	@Column(name="cmt")
	String comment;
	
	/**
	 * 발령 명단
	 */
	@OneToMany(mappedBy = "ledger", cascade = CascadeType.ALL, orphanRemoval = true )
	@MapKeyColumn(name="LIST_ID", insertable = false, updatable = false, nullable = false)
	Map<String, AppointmentList> appointmentList = new HashMap<>();

	/**
	 * @param id
	 * @param appointmentType
	 * @param registrationDate
	 * @param comment
	 */
	public AppointmentRegister(String id
							  ,String appointmentType
							  ,LocalDate registrationDate
							  ,String comment) {
		this.id = id;
		this.appointmentType = appointmentType;
		this.registrationDate = registrationDate;
		this.comment = comment;
	}
	
	public void changeInfo(String appointmentType
						  ,String comment) {
		this.appointmentType = appointmentType;
		this.comment = comment;		
	}
	
	public AppointmentList getAppointmentList(String listId) {			
		return this.appointmentList.get(listId);
	}
	
	public void addAppointmentList(AppointmentList list) {
		if (this.appointmentList == null)
			this.appointmentList = new HashMap<>();
		
		this.appointmentList.put(list.getListId(), list);
	}
	
	public void deleteAppointmentList(String pk) {
		if (!this.appointmentList.containsKey(pk)) {
			throw new EntityNotFoundException(pk+ "가 존재하지 않습니다.");
		}
		
		AppointmentList list = this.getAppointmentList(pk);
		
		if (list.getFinishYn()) {
			throw new IllegalStateException("처리가 왼료된 발령입니다.");
		}
		
		this.appointmentList.remove(pk);
	}
			
	
}
