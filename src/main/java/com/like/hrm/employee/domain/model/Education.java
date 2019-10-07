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
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMEMPEDUCATION")
@EntityListeners(AuditingEntityListener.class)
public class Education extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 5879415854289672377L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
		
	/**
	 * 학력유형
	 */
	@Column(name="EDU_TYPE")
	private String eduType;
	
	/**
	 * 학교코드
	 */
	@Column(name="SCHOOL_CODE")
	private String schoolCode;
	
	/**
	 * 설명
	 */
	@Column(name="CMT")
	private String comment;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;
	
	public Education(Employee employee
				    ,String eduType
				    ,String schoolCode
				    ,String comment) {
		this.employee = employee;
		this.eduType = eduType;
		this.schoolCode = schoolCode;
		this.comment = comment;
	}
	
	public void modifyEntity(String eduType
		    				,String schoolCode
							,String comment) {
		this.eduType = eduType;
		this.schoolCode = schoolCode;
		this.comment = comment;		
	}
				
}
