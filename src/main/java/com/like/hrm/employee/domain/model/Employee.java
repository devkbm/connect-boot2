package com.like.hrm.employee.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;
import com.like.hrm.appointment.domain.model.Appointable;
import com.like.hrm.appointment.domain.model.LedgerList;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"deptHistory","jobHistory"})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "HRMEMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
public class Employee extends AuditEntity implements Serializable, Appointable {

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
		
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	List<DeptChangeHistory> deptHistory = new ArrayList<>();
		
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	List<JobChangeHistory> jobHistory = new ArrayList<>();		
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	List<StatusChangeHistory> statusHistory = new ArrayList<>();
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	Set<License> licenseList = new LinkedHashSet<>();
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	Set<Education> educationList = new LinkedHashSet<>();
	
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
		
	public String getEmployeeId() {
		return this.id;
	}
	
	public List<DeptChangeHistory> getDeptChangeHistory() {
		return this.deptHistory;
	}
	
	public List<JobChangeHistory> getJobChangeHistory() {
		return this.jobHistory;
	}
	
	public List<StatusChangeHistory> getStatusChangeHistory() {
		return this.statusHistory;
	}
	
	/**
	 * <p>부서변경이력을 추가한다.</p>
	 * @param deptChangeHistory
	 */
	public void addDeptChange(DeptChangeHistory deptChangeHistory) {
		
		this.terminateDept(deptChangeHistory.getDeptType()
						  ,deptChangeHistory.getFromDate());
		
		deptHistory.add(deptChangeHistory);
	}	
	
	/**
	 * <p>부서 이력 중 기준일자에 해당하는 사용가능한 이력이 있을 경우 전일로 종료시킨다.</p>
	 * @param deptType
	 * @param referenceDate
	 */
	public void terminateDept(String deptType, LocalDate referenceDate) {
				
		for (DeptChangeHistory deptHistory: this.getDeptChangeHistory()) {
			
			if (referenceDate.isBefore(deptHistory.getFromDate())
			 && deptHistory.equalDeptType(deptType)) {
				throw new IllegalArgumentException(deptHistory.getFromDate() + "일로 시작하는 부서 이력이 존재합니다.");
			}
			
			if ( deptHistory.equalDeptType(deptType)			  	
			  && deptHistory.isEnabled(referenceDate) )
				
				deptHistory.terminateHistory(referenceDate.minusDays(1));				
		}									
		
	}
	
	/**
	 * <p>인사정보이력을 추가한다.</p>
	 * @param jobChangeHistory
	 */
	public void addJobChange(JobChangeHistory jobChangeHistory) {
		this.terminateJob(jobChangeHistory.getJobType()
						 ,jobChangeHistory.getFromDate());
		
		jobHistory.add(jobChangeHistory);
	}
	
	/**
	 * <p>인사정보 이력을 종료일자 기준으로 종료시킨다.</p>
	 * @param jobType
	 * @param referenceDate
	 */
	public void terminateJob(String jobType, LocalDate referenceDate) {
		
		for (JobChangeHistory jobHistory: this.getJobChangeHistory()) {
			
			if (jobHistory.equalJobType(jobType) 
			 && referenceDate.isBefore(jobHistory.getFromDate())) {
				throw new IllegalArgumentException(jobHistory.getFromDate() + "일로 시작하는 이력이 존재합니다.");
			}
			
			if ( jobHistory.equalJobType(jobType) 			  
			  && jobHistory.isEnabled(referenceDate) )
				
				jobHistory.terminateHistory(referenceDate.minusDays(1));				
		}
	}
	
	public void changeStatus(String appointmentCode, String statusCode, LocalDate fromDate, LocalDate toDate) {
		for (StatusChangeHistory statusHistory: this.getStatusChangeHistory()) {
			if (fromDate.isBefore(statusHistory.getFromDate())) {
				throw new IllegalArgumentException(statusHistory.getFromDate() + "일로 시작하는 이력이 존재합니다.");
			}
			
			if (statusHistory.isEnabled(fromDate)) {
				statusHistory.terminateHistory(fromDate.minusDays(1));
			}
		}
					
		this.statusHistory.add(new StatusChangeHistory(this, appointmentCode, statusCode, fromDate, toDate));
	}

	public void changeImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	@Override
	public void appoint(LedgerList ledgerDetail) {

	}

	public void addLicense(License license) {
		if (this.licenseList == null) {
			this.licenseList = new LinkedHashSet<>();
		}
		
		this.licenseList.add(license);
	}
	
	public License getLicense(Long id) {
		return null;
	}


	
	
}
