package com.like.hrm.appointment.domain.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.hrm.appointment.domain.model.enums.ChangeType;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>직위 유형 코드 기준 정보</p> 
 * [상세] <br/>
 * 1.  
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@DiscriminatorValue(value = ChangeType.Values.JOB)
public class JobType extends ChangeableType implements Serializable {
		
	private static final long serialVersionUID = -5111930508978559883L;	
	
	/**
	 * @param id
	 * @param code
	 * @param codeName
	 * @param useYn
	 */
	public JobType(String code
				  ,String codeName
				  ,boolean useYn
				  ,Integer sequence
				  ,String comment) {		
		this.id = ChangeType.Values.JOB + code;
		this.code = code;
		this.codeName = codeName;		
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}

}
