package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
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

/**
 * <p>발령대장 명단 정보</p> 
 * [상세] <br/>
 * 1. <br/>
 * [제약사항] <br/>
 * 1. <br/>
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true, exclude = {"changeInfoList","ledger"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@EqualsAndHashCode(of = {"listId"}, callSuper = false)
@Getter
@Entity
@Table(name = "HRMAPPOINTMENTLEDGERLIST")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentList extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 8498392159292587566L;

	/**
	 * 식별자
	 */
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LIST_ID")
	String listId;
	
	/**
	 * 순번
	 */
	@OrderBy
	@Column(name="SEQ")
	Long sequence;
	
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
	 * 완료여부
	 */
	@Column(name="FINISH_YN")
	Boolean finishYn;
	
	/**
	 * 발령변경정보
	 */
	@OneToMany(mappedBy = "ledgerList", fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	List<AppointmentChangeInfo> changeInfoList = new ArrayList<>();
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name="LEDGER_ID", nullable=true, updatable=true)
	private AppointmentRegister ledger;
	
	public AppointmentList(String empId
						  ,String appointmentCode
 					 	  ,LocalDate appointmentFromDate
						  ,LocalDate appointmentToDate) {													
		this.empId = empId;
		this.appointmentCode = appointmentCode;
		this.appointmentFromDate = appointmentFromDate;
		this.appointmentToDate = appointmentToDate;
		this.sequence = 0L;
		this.finishYn = false;			
	}
	
	public void modifyEntity(String appointmentCode
							,LocalDate appointmentFromDate
			           		,LocalDate appointmentToDate
			           		,AppointmentRegister ledger) {
		
		// 발령 코드 변경시 상세내역 삭제
		if (this.appointmentCode.equals(appointmentCode) != true) {
			this.clearChangeInfo();			
		}
		
		this.appointmentCode = appointmentCode;
		this.appointmentFromDate = appointmentFromDate;
		this.appointmentToDate = appointmentToDate;
		this.ledger = ledger;
	}
	
	public void addChangeInfo(AppointmentChangeInfo changeInfo) {		
		if (this.changeInfoList == null) 
			this.changeInfoList = new ArrayList<>();
		
		this.changeInfoList.add(changeInfo);
	}
	
	public boolean isContainChangeInfo(Long changeId) {
		boolean rtn = false;
		
		for (AppointmentChangeInfo info : this.changeInfoList) {
			if ( info.getId().equals(changeId) ) 
				rtn = true;
		}
		
		return rtn;
	}
	
	public AppointmentChangeInfo getChangeInfo(Long changeId) {			
		AppointmentChangeInfo result = null;
		
		if (!this.changeInfoList.isEmpty()) {
			for (AppointmentChangeInfo info : this.changeInfoList) {
				
				if ( changeId.equals(info.getId()) ) 
					result = info;
			}
		}
		return result;
	}
	
	public void clearChangeInfo() {
		this.changeInfoList.clear();
	}
	
	public void finish() {
		this.finishYn = true;
	}
	
}
