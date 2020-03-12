package com.like.hrm.appointment.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.hrm.appointment.domain.model.enums.ChangeType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>부서 유형 코드 기준 정보</p> 
 * [상세] <br/>
 * 1. 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMTYPECODEDETAIL")
public class TypeDetailCode implements Serializable {		
	
	private static final long serialVersionUID = 5468996305272335478L;

	@Id	
	@Column(name="DETAIL_ID")
	private String id;
	
	@Column(name="TYPE_ID")
	private String typeId;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="CODE_NAME")
	private String codeName;
		
	@Column(name="USE_YN")
	private boolean useYn = true;

	@Column(name="PRT_SEQ")
	private Integer sequence;
	
	@Column(name="CMT")
	private String comment;		
	
	/**
	 * 
	 * @param typeId
	 * @param code
	 * @param codeName
	 * @param useYn
	 * @param sequence
	 * @param comment
	 */
	public TypeDetailCode(String typeId
						 ,String code
						 ,String codeName
						 ,boolean useYn
						 ,Integer sequence
						 ,String comment) {		
		this.id = typeId + code;
		this.typeId = typeId;
		this.code = code;
		this.codeName = codeName;		
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}
		
	public void changeInfo(String codeName
						  ,boolean useYn
						  ,Integer sequence
						  ,String comment ) {	
		this.codeName = codeName;
		this.useYn = useYn;
		this.sequence = sequence;
		this.comment = comment;
	}	
	
}
