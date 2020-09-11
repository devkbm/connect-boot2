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

@JsonIgnoreProperties(ignoreUnknown = true, value = {"employee"})
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMEMPLICENSE")
@EntityListeners(AuditingEntityListener.class)
public class Family extends AuditEntity implements Serializable {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;
	
	/**
	 * 가족 성명
	 */
	private String name;
	
	/**
	 * 주민등록번호
	 */
	private String residentRegistrationNumber;
	
	/**
	 * 가족관계
	 */
	private String relation;
	
	/**
	 * 직업
	 */
	private String occupation;
	
	/**
	 * 학력구분
	 */
	private String eduType;
	
	/**
	 * 비고
	 */
	private String comment;
	
	
	public Family(Employee employee
				 ,String name
				 ,String residentRegistrationNumber
				 ,String relation
				 ,String occupation
				 ,String eduType
				 ,String comment) {
		this.employee = employee;
		this.name = name;
		this.residentRegistrationNumber = residentRegistrationNumber;
		this.relation = relation;
		this.occupation = occupation;
		this.eduType = eduType;
		this.comment = comment;
	}
	
	public void modifyEntity(String name
							,String residentRegistrationNumber
							,String relation
							,String occupation
							,String eduType
							,String comment) {
		this.name = name;
		this.residentRegistrationNumber = residentRegistrationNumber;
		this.relation = relation;
		this.occupation = occupation;
		this.eduType = eduType;
		this.comment = comment;
	}
}
