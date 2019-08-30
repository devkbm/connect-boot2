package com.like.hrm.employee.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;
import com.like.hrm.appointment.domain.model.Appointable;
import com.like.hrm.appointment.domain.model.AppointmentLedgerDetail;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	List<DeptChangeHistory> deptHistory = new ArrayList<>();
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	List<JobChangeHistory> jobHistory = new ArrayList<>();		
	
	@Builder
	public Employee(String id, 
					String name, 
					String nameEng, 
					String nameChi, 
					String residentRegistrationNumber,
					String workCondition) {		
		this.id = id;
		this.name = name;
		this.nameEng = nameEng;
		this.nameChi = nameChi;
		this.residentRegistrationNumber = residentRegistrationNumber;
		this.workCondition = workCondition;
	}
	
	
	public String getEmployeeId() {
		return this.id;
	}
	
	public void addDeptChange(DeptChangeHistory deptChangeHistory) {
		
		deptHistory.add(deptChangeHistory);
	}
	
	public void addJobChange(JobChangeHistory jobChangeHistory) {
		jobHistory.add(jobChangeHistory);
	}
	
	/**
	 * <p>부서 이력을 종료일자 기준으로 종료시킨다.</p>
	 * @param deptType
	 * @param deptCode
	 * @param terminateDate
	 */
	public void terminateDept(String deptType, String deptCode, LocalDate terminateDate) {
		
		for (DeptChangeHistory deptHistory: this.deptHistory) {
			if ( deptHistory.equalDeptType(deptType)
			  && deptHistory.equalDeptCode(deptCode)		
			  && deptHistory.isEnabled(terminateDate) )
				
				deptHistory.terminateHistory(terminateDate);				
		}									
		
	}
	
	/**
	 * <p>인사정보 이력을 종료일자 기준으로 종료시킨다.</p>
	 * @param jobType
	 * @param jobCode
	 * @param terminateDate
	 */
	public void terminateJob(String jobType, String jobCode, LocalDate terminateDate) {
		
		for (JobChangeHistory jobHistory: this.jobHistory) {
			if ( jobHistory.equalJobType(jobType) 
			  && jobHistory.equalJobCode(jobCode)
			  && jobHistory.isEnabled(terminateDate) )
				
				jobHistory.terminateHistory(terminateDate);				
		}
	}

	@Override
	public void appoint(AppointmentLedgerDetail ledgerDetail) {

	}
	
	
}
