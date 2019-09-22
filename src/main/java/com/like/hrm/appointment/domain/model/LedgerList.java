package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.common.domain.AuditEntity;

import java.util.ArrayList;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@EqualsAndHashCode(of = {"listId"}, callSuper = false)
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
	LocalDate appointmentFromDate;
	
	/**
	 * 발령종료일자
	 */
	@Column(name="TO_DT")
	LocalDate appointmentToDate;
	
	/**
	 * 발령변경정보
	 */
	@OneToMany(mappedBy = "ledgerList", fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	List<LedgerChangeInfo> changeInfoList = new ArrayList<>();
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name="LEDGER_ID", nullable=false, updatable=false)
	private Ledger ledger;
	
	public LedgerList(Ledger ledger
					 ,String empId
					 ,String appointmentCode
				 	 ,LocalDate appointmentFromDate
					 ,LocalDate appointmentToDate) {
		this.ledger = ledger;		
		this.empId = empId;
		this.appointmentCode = appointmentCode;
		this.appointmentFromDate = appointmentFromDate;
		this.appointmentToDate = appointmentToDate;		
		
		// 식별자 생성
		this.listId = this.empId + this.appointmentCode + this.appointmentFromDate.format(DateTimeFormatter.ofPattern("YYYYMMDD"));
	}
	
	public void modifyEntity(LocalDate appointmentToDate) {
		this.appointmentToDate = appointmentToDate;		
	}
	
	public void addChangeInfo(LedgerChangeInfo changeInfo) {		
		if (this.changeInfoList == null) 
			this.changeInfoList = new ArrayList<>();
		
		this.changeInfoList.add(changeInfo);
	}
	
	public boolean isContainChangeInfo(Long changeId) {
		boolean rtn = false;
		
		for (LedgerChangeInfo info : this.changeInfoList) {
			if ( info.getId().equals(changeId) ) 
				rtn = true;
		}
		
		return rtn;
	}
	
	public LedgerChangeInfo getChangeInfo(Long changeId) {			
		LedgerChangeInfo result = null;
		
		if (!this.changeInfoList.isEmpty()) {
			for (LedgerChangeInfo info : this.changeInfoList) {
				log.info(info.toString());
				if ( changeId.equals(info.getId()) ) 
					result = info;
			}
		}
		return result;
	}
}
