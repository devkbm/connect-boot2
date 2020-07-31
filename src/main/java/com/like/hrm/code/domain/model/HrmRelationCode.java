package com.like.hrm.code.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMRELATIONCODE")
public class HrmRelationCode extends AuditEntity {

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="RELATION_ID")
	private Long relationId;
		
	/**
	 * COMCODE테이블 P_CODE 컬럼 : HRMREL 참조
	 */
	@Column(name="REL_CODE")
	private String relCode;
	
	@Column(name="PARENT_TYPE_ID")
	private String parentTypeId;
	
	@Column(name="PARENT_DETAIL_ID")
	private String parentDetailId;
	
	@Column(name="CHILD_TYPE_ID")
	private String childTypeId;
	
	@Column(name="CHILD_DETAIL_ID")
	private String childDetailId;
	
	public HrmRelationCode(String relCode
						  ,String parentTypeId
						  ,String parentDetailId
						  ,String childTypeId
						  ,String childDetailId) {
		this.relCode = relCode;
		this.parentTypeId = parentTypeId;
		this.parentDetailId = parentDetailId;
		this.childTypeId = childTypeId;		
		this.childDetailId = childDetailId;
	}	
	
}
