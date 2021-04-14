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
import com.like.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"employee"})
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMEMPFAMILY")
@EntityListeners(AuditingEntityListener.class)
public class Family extends AuditEntity implements Serializable {
			
	private static final long serialVersionUID = -3377701513438383323L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;
	
	/**
	 * 가족 성명
	 */
	@Column(name="FAMILY_NAME", nullable = false)
	private String name;
	
	/**
	 * 주민등록번호
	 */
	@Column(name="RREGNO", nullable = false)
	private String residentRegistrationNumber;
	
	/**
	 * 가족관계
	 */
	@Column(name="FAMILY_REL_CODE", nullable = false)
	private String relation;
	
	/**
	 * 직업
	 */
	@Column(name="OCCUPATION_NAME", nullable = true)
	private String occupation;
	
	/**
	 * 학력구분
	 */
	@Column(name="SCHOOL_CAREER_TYPE", nullable = true)
	private String schoolCareerType;
	
	/**
	 * 비고
	 */
	@Column(name="CMT", nullable = true)
	private String comment;
	
	
	public Family(Employee employee
				 ,String name
				 ,String residentRegistrationNumber
				 ,String relation
				 ,String occupation
				 ,String schoolCareerType
				 ,String comment) {
		this.employee = employee;
		this.name = name;
		this.residentRegistrationNumber = residentRegistrationNumber;
		this.relation = relation;
		this.occupation = occupation;
		this.schoolCareerType = schoolCareerType;
		this.comment = comment;
	}
	
	public void modifyEntity(String name
							,String residentRegistrationNumber
							,String relation
							,String occupation
							,String schoolCareerType
							,String comment) {
		this.name = name;
		this.residentRegistrationNumber = residentRegistrationNumber;
		this.relation = relation;
		this.occupation = occupation;
		this.schoolCareerType = schoolCareerType;
		this.comment = comment;
	}
}
