package com.like.hrm.employee.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
import com.like.common.vo.DatePeriod;

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
@EqualsAndHashCode(callSuper = false, of = {"educationId"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMEMPEDUCATION")
@EntityListeners(AuditingEntityListener.class)
public class SchoolCareer extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 5879415854289672377L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long educationId;
		
	/**
	 * 학력유형
	 */
	@Column(name="EDU_TYPE")
	private String schoolCareerType;
	
	/**
	 * 학교코드
	 */
	@Column(name="SCHOOL_CODE")
	private String schoolCode;
	
	
	@Embedded
	DatePeriod period;
	
	/**
	 * 전공학과명
	 */
	private String majorName;
	
	/**
	 * 복수전공학과명
	 */
	private String pluralMajorName;
	
	/**
	 * 소재지
	 */	
	private String location;
	
	/**
	 * 수업연한
	 */
	private Integer lessonYear;
	
	/**
	 * 설명
	 */
	@Column(name="CMT")
	private String comment;
		
	// 시작일, 종료일, 전공학과명, 복수전공학과명, 학교소재지, 수업연한, 입사학력여부, 수고권대학여부, 야간여부, 이공계여부, 이미지
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;
	
	public SchoolCareer(Employee employee
				    ,String schoolCareerType
				    ,String schoolCode
				    ,String comment) {
		this.employee = employee;
		this.schoolCareerType = schoolCareerType;
		this.schoolCode = schoolCode;
		this.comment = comment;
	}
	
	public void modifyEntity(String eduType
		    				,String schoolCareerType
							,String comment) {
		this.schoolCareerType = schoolCareerType;
		this.schoolCode = schoolCode;
		this.comment = comment;		
	}
				
}
