package com.like.hrm.employee.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>부서 이력 관리 클래스</p>
 * 
 * Unique Index : EMP_ID, DEPT_TYPE, DEPT_CODE <br>
 * [상세] <br>
 * 1. <br>
 * 2. <br>
 * @author 김병민
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = {"employee"})
@EqualsAndHashCode(callSuper = false, of = {"licenseId"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMEMPLICENSE")
@EntityListeners(AuditingEntityListener.class)
public class License extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 5879415854289672377L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long licenseId;
		
	/**
	 * 자격면허유형
	 */
	@Column(name="LICENSE_TYPE")
	private String licenseType;
	
	/**
	 * 자격면허코드
	 */
	@Column(name="LICENSE_CODE")
	private String licenseCode;
	
	/**
	 * 설명
	 */
	@Column(name="CMT")
	private String comment;
	
	// 취득일자, 자격면허, 자격면허인가번호, 발행기관, 필수면허번호여부, 이미지
		
	// 가족 - 가족성명, 주민등록번호, 가족관계, 직업, 학력, 비고, 건강보험등재
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;
	
	public License(Employee employee
				  ,String licenseType
				  ,String licenseCode
				  ,String comment) {
		this.employee = employee;
		this.licenseType = licenseType;
		this.licenseCode = licenseCode;
		this.comment = comment;
	}
	
	public void modifyEntity(String licenseType
							,String licenseCode
							,String comment) {
		this.licenseType = licenseType;
		this.licenseCode = licenseCode;
		this.comment = comment;		
	}
				
}
