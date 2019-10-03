package com.like.commoncode.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"parentCode"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@Getter
@Entity
@Table(name = "comcode")
@EntityListeners(AuditingEntityListener.class)
public class Code extends AuditEntity implements Serializable {
			
	private static final long serialVersionUID = 1122730947003822818L;
	
	@Id
	@Column(name="code_id")
	String id;
		
	/*@Column(name="p_code_id")
	String parentId;*/
	
	@Column(name="code")
	String code;
		
	@Column(name="code_name")
	String codeName;
	
	@Column(name="code_name_abbr")
	String codeNameAbbreviation;		
	
	@Column(name="from_dt")
	LocalDateTime fromDate;
	
	@Column(name="to_dt")
	LocalDateTime toDate;
	
	@Column(name="hierarchy_level")
	int hierarchyLevel = 1;
	
	@Column(name="prt_seq")
	int seq = 0;
			
	@Column(name="fixed_length_yn")
	boolean fixedLengthYn = true;
	
	@Column(name="code_length")
	Integer codeLength;
	
	@Column(name="cmt")
	String cmt;
			
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_code_id")
	Code parentCode;

	@Builder
	public Code(String code
			   ,String codeName
			   ,String codeNameAbbreviation
			   ,LocalDateTime fromDate
			   ,LocalDateTime toDate
			   ,int seq
			   ,boolean fixedLengthYn
			   ,Integer codeLength
			   ,String cmt
			   ,Code parentCode) {
		
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
			this.id = this.code;			
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
