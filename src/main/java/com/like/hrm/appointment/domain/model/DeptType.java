package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>부서 유형 코드 기준 정보</p> 
 * [상세] <br/>
 * 1. 공통코드 상위코드 : HRMH0002
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "comcode")
@EntityListeners(AuditingEntityListener.class)
public class DeptType extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -7607475813346542493L;
	
	@Id
	@Column(name="code_id")
	private String id;
	
	@Column(name="p_code_id")
	private String parentId;
		
	@Column(name="code")
	String code;
	
	@Column(name="code_name")
	String codeName;
		
	@Column(name="use_yn")
	boolean useYn = true;
	
	@Column(name="from_dt")
	LocalDateTime fromDate;
	
	@Column(name="to_dt")
	LocalDateTime toDate;
	
	@Column(name="prt_seq")
	Integer sequence;
	
	@Builder
	public DeptType(String code, String codeName, boolean useYn, LocalDateTime fromDate, LocalDateTime toDate,
			Integer sequence) {
		super();
		this.parentId = "HRMH0002";
		this.id = "HRMH0002"+this.code;
		this.code = code;
		this.codeName = codeName;
		this.useYn = useYn;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.sequence = sequence;
	}

}
