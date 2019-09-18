package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Entity
@Table(name = "HRMAPPOINTMENTLEDGER")
@EntityListeners(AuditingEntityListener.class)
public class Ledger extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -5893205308411172278L;

	/**
	 * 원장 번호
	 */
	@Id	
	@Column(name="LEDGER_ID")
	String LedgerId;
		
	/**
	 * 발령 유형(정기, 임의)
	 */
	@Column(name="RGST_DT")
	String AppointmentType;
	
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
	@MapKeyColumn(name="LEDGER_ID", insertable = false, updatable = false, nullable = false)
	Map<String, LedgerList> appointmentList = new HashMap<>();
			
	
}
