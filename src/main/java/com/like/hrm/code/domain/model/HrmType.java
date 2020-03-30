package com.like.hrm.code.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMTYPECODE")
public class HrmType extends AuditEntity {
	
	@Id	
	@Column(name="TYPE_ID")
	private String id;

	@Enumerated(EnumType.STRING)
	@Column(name="TYPE_CODE")
	private HrmTypeEnum hrmType;
		
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
	 * @param id
	 * @param code
	 * @param codeName
	 * @param useYn
	 * @param sequence
	 * @param comment
	 */
	public HrmType(HrmTypeEnum hrmType
				  ,String code
				  ,String codeName
				  ,boolean useYn
				  ,Integer sequence
				  ,String comment) {
		this.id = hrmType + code;
		this.hrmType = hrmType;
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
