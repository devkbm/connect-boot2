package com.like.hrm.employee.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;
import com.like.hrm.employee.domain.model.vo.DeptChangeList;
import com.like.hrm.employee.domain.model.vo.FamilyList;
import com.like.hrm.employee.domain.model.vo.JobChangeList;
import com.like.hrm.employee.domain.model.vo.LicenseList;
import com.like.hrm.employee.domain.model.vo.SchoolCareerList;
import com.like.hrm.employee.domain.model.vo.StatusChangeList;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@JsonIgnoreProperties(ignoreUnknown = true, value = {"deptHistory","jobHistory"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMEMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
public class Employee extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -3164713918774455925L;

	/**
	 * 사원번호
	 */
	@Id
	@Column(name="EMP_ID")
	String id;	
	
	/**
	 * 한글 성명
	 */
	@Column(name="EMP_NAME")
	String name;
	
	/**
	 * 영문 성명
	 */
	@Column(name="EMP_NAME_ENG")
	String nameEng;
	
	/**
	 * 한문 성명
	 */
	@Column(name="EMP_NAME_CHI")
	String nameChi;
	
	/**
	 * 법적 이름
	 */
	@Column(name="EMP_NAME_LEGAL")
	String legalName;
		
	/**
	 * 주민번호
	 */
	@Column(name="RREGNO")
	String residentRegistrationNumber;
	
	/**
	 * 성별
	 */
	@Column(name="GENDER")
	String gender;
	
	/**
	 * 생일
	 */
	@Column(name="BIRTHDAY")
	LocalDate birthday;	
	
	/**
	 * 근무상태
	 */
	@Column(name="WORK_CONDITION")
	String workCondition;
	
	/**
	 * 이미지경로
	 */
	@Column(name="IMG_PATH")
	String imagePath;
		
	/**
	 * 부서이력
	 */
	@Embedded
	DeptChangeList deptHistory;
		
	/**
	 * 직위 직급 등 인사정보 이력
	 */
	@Embedded
	JobChangeList jobHistory;
		
	/**
	 * 근무상태 이력
	 */
	@Embedded
	StatusChangeList statusHistory;	
	
	/**
	 * 가족 명단
	 */
	@Embedded
	FamilyList familyList;
	
	/**
	 * 자격면허 명단
	 */
	@Embedded
	LicenseList licenseList;
		
	/**
	 * 학력이력
	 */
	@Embedded
	SchoolCareerList schoolCareerList;	
	
	public Employee(String id
				   ,String name
				   ,String nameEng
				   ,String nameChi
				   ,String residentRegistrationNumber) {
		this.id = id;
		this.name = name;
		this.nameEng = nameEng;
		this.nameChi = nameChi;
		this.residentRegistrationNumber = residentRegistrationNumber;
		this.workCondition = "Z";
	}
	
	public void modifyEntity(String name
						    ,String nameEng
						    ,String nameChi
						    ,String gender
						    ,LocalDate birthday) {
		this.name 		= name;
		this.nameEng 	= nameEng;
		this.nameChi 	= nameChi;
		this.gender 	= gender;
		this.birthday 	= birthday;
	}
		
	public String getEmployeeId() {
		return this.id;
	}			

	public void changeImagePath(String imagePath) {
		this.imagePath = imagePath;
	}		

}
