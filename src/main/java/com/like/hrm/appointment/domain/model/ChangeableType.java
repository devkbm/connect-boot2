package com.like.hrm.appointment.domain.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMTYPECODE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_code")
public abstract class ChangeableType extends AuditEntity {
	
	@Id	
	@Column(name="TYPE_ID")
	protected String id;

	@Column(name="CODE")
	protected String code;
	
	@Column(name="CODE_NAME")
	protected String codeName;
		
	@Column(name="USE_YN")
	protected boolean useYn = true;

	@Column(name="PRT_SEQ")
	protected Integer sequence;
	
	@Column(name="CMT")
	protected String comment;		

	/**
	 * @param id
	 * @param code
	 * @param codeName
	 * @param useYn
	 * @param sequence
	 * @param comment
	 */
	protected ChangeableType(String id
							,String code
							,String codeName
							,boolean useYn
							,Integer sequence
							,String comment) {
		this.id = id;
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
