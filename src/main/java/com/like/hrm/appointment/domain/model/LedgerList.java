package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.common.domain.AuditEntity;

import java.util.ArrayList;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Entity
@Table(name = "HRMAPPOINTMENTLEDGERLIST")
@EntityListeners(AuditingEntityListener.class)
public class LedgerList extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 8498392159292587566L;

	/**
	 * 식별자 : 직원ID + 발령코드 + 발령일자
	 */
	@Id	
	@Column(name="LIST_ID")
	String listId;
	
	/**
	 * 직원 ID
	 */
	@Column(name="EMP_ID")
	String empId;
		
	/**
	 * 발령코드
	 */
	@Column(name="APPOINTMENT_CODE")
	String appointmentCode;
	
	/**
	 * 발령시작일자
	 */
	@Column(name="FROM_DT")
	LocalDate AppointmentFromDate;
	
	/**
	 * 발령종료일자
	 */
	@Column(name="TO_DT")
	LocalDate AppointmentToDate;
	
	/**
	 * 발령변경정보
	 */
	@OneToMany(mappedBy = "ledgerList", fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	List<LedgerChangeInfo> changeInfoList = new ArrayList<>();
	
	@ManyToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name="LEDGER_ID", nullable=false, updatable=false)
	private Ledger ledger;
}
