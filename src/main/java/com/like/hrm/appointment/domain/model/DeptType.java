package com.like.hrm.appointment.domain.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.like.hrm.appointment.domain.model.enums.ChangeType;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>부서 유형 코드 기준 정보</p> 
 * [상세] <br/>
 * 1. 
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper=true, includeFieldNames=true)
@Entity
@DiscriminatorValue(value = ChangeType.Values.DEPT)
public class DeptType extends ChangeableType implements Serializable {
	
	private static final long serialVersionUID = -7607475813346542493L;		

	/**
	 * @param id
	 * @param code
	 * @param codeName
	 * @param useYn
	 */
	public DeptType(String code
				   ,String codeName
				   ,boolean useYn
				   ,Integer sequence
				   ,String comment) {		
		this.id = ChangeType.Values.DEPT + code;
		this.code = code;
		this.codeName = codeName;		
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}
		
	
}
