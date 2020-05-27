package com.like.hrm.code.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMRELATIONCODE")
public class HrmRelationCode {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RELATION_ID")
	private Long relationId;
		
	/**
	 * COMCODE테이블 P_CODE 컬럼 : HRMREL 참조
	 */
	@Column(name="REL_CODE")
	private String relCode;
	
	@Column(name="PARENT_ID")
	private String parentId;
	
	@Column(name="CHILD_ID")
	private String childId;
	
	public HrmRelationCode(String relCode
						  ,String parentId
						  ,String childId) {
		this.relCode = relCode;
		this.parentId = parentId;
		this.childId = childId;		
	}
	
}
