package com.like.hrm.appointment.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.like.common.domain.AuditEntity;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>발령대장 명단 변경 정보</p> 
 * [상세] <br/>
 * 1. <br/>
 * [제약사항] <br/>
 * 1. <br/>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true, exclude = {"ledgerList"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@EqualsAndHashCode(of = {"id"}, callSuper = false)
@Getter
@Entity
@Table(name = "HRMAPPOINTMENTINFO")
@EntityListeners(AuditingEntityListener.class)
public class LedgerChangeInfo extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 6755495351146205498L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
				
	@Column(name="CHANGE_TYPE")
	@Enumerated(EnumType.STRING)
	HrmTypeEnum changeType;
		
	@Column(name="CHANGE_TYPE_DETAIL")
	String changeTypeDetail;
	
	@Column(name="CODE")
	String changeCode;
	
	@OrderBy
	@Column(name="PRT_SEQ")
	Integer sequence;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name="LIST_ID", nullable=false, updatable=false)
	LedgerList ledgerList;
	
	public LedgerChangeInfo(LedgerList ledgerList
						   ,HrmTypeEnum changeType
						   ,String changeTypeDetail
						   ,String changeCode
						   ,Integer sequence) {
		this.ledgerList = ledgerList;
		this.changeType = changeType;
		this.changeTypeDetail = changeTypeDetail;
		this.changeCode = changeCode;		
		this.sequence = sequence;
	}
	
	public void changeCode(String code
						  ,Integer sequence) {
		this.changeCode = code;
		this.sequence = sequence;
	}
}
