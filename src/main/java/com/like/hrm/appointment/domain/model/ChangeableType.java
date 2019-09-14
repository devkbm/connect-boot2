package com.like.hrm.appointment.domain.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

@Entity
@Table(name = "comcode")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "")
public abstract class ChangeableType extends AuditEntity {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	Long id;
	
	@Column(name="code")
	String code;
	
	@Column(name="code_name")
	String codeName;
		
	@Column(name="use_yn")
	boolean useYn = true;

}
