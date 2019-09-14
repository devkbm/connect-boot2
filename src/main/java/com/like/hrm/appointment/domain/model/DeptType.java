package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
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
@Entity
@DiscriminatorValue(value = "DEPT")
public class DeptType extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -7607475813346542493L;
		

}
