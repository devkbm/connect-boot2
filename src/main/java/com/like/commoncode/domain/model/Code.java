package com.like.commoncode.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true, exclude = {"parentCode"})
@Getter
@Entity
@Table(name = "comcode")
@EntityListeners(AuditingEntityListener.class)
public class Code extends AuditEntity  {
					
	@Id
	@Column(name="CODE_ID")
	String id;		
	
	@Column(name="SYSTEM_TYPE_CODE")
	String systemTypeCode;
	
	@Column(name="CODE")
	String code;
		
	@Column(name="CODE_NAME")
	String codeName;
	
	@Column(name="CODE_NAME_ABBR")
	String codeNameAbbreviation;		
	
	@Column(name="FROM_DT")
	LocalDateTime fromDate;
	
	@Column(name="TO_DT")
	LocalDateTime toDate;
	
	@Column(name="HIERARCHY_LEVEL")
	int hierarchyLevel = 1;
	
	@Column(name="PRT_SEQ")
	int seq = 0;
			
	@Column(name="FIXED_LENGTH_YN")
	boolean fixedLengthYn = true;
	
	@Column(name="CODE_LENGTH")
	Integer codeLength;
	
	@Transient
	String subGroup1;
	
	@Transient
	String subGroup2;
	
	@Transient
	String subGroup3;
	
	@Column(name="cmt")
	String cmt;
			
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_code_id")
	Code parentCode;

	@Builder
	public Code(String systemTypeCode
			   ,String code
			   ,String codeName
			   ,String codeNameAbbreviation
			   ,LocalDateTime fromDate
			   ,LocalDateTime toDate
			   ,int seq
			   ,boolean fixedLengthYn
			   ,Integer codeLength
			   ,String cmt
			   ,Code parentCode) {		
		this.systemTypeCode = systemTypeCode;
		this.code = code;
		this.codeName = codeName;
		this.codeNameAbbreviation = codeNameAbbreviation;		
		this.fromDate = fromDate;
		this.toDate = toDate;		
		this.seq = seq;
		this.fixedLengthYn = fixedLengthYn;
		this.codeLength = codeLength;
		this.cmt = cmt;
		this.parentCode = parentCode;
		
		this.createId();
		this.hierarchyLevel();
	}
	
	/**
	 * @param codeName
	 * @param codeNameAbbreviation
	 * @param fromDate
	 * @param toDate
	 * @param seq
	 * @param fixedLengthYn
	 * @param codeLength
	 * @param cmt
	 */
	public void modifyEntity(String codeName
							,String codeNameAbbreviation
							,LocalDateTime fromDate
							,LocalDateTime toDate
							,int seq
							,boolean fixedLengthYn
							,Integer codeLength
							,String cmt) {
		this.codeName = codeName;
		this.codeNameAbbreviation = codeNameAbbreviation;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.seq = seq;
		this.fixedLengthYn = fixedLengthYn;
		this.codeLength = codeLength;
		this.cmt = cmt;
	}
	

	private String createId() {
		
		if ( this.parentCode == null ) {
			this.id = this.systemTypeCode + this.code;			
		} else {
			this.id = this.parentCode.id + this.code;
		}
		
		return this.id;
	}
	
	private void hierarchyLevel() {
		if ( this.parentCode == null ) {
			this.hierarchyLevel = 1; 
		} else {
			this.hierarchyLevel = this.parentCode.hierarchyLevel + 1;
		}
	}
	
	public Code getParentCode() {
		return this.parentCode;
	}
	
	public String getId() {
		return this.id;
	}
					
}
