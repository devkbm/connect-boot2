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
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMEMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
public class Employee extends AuditEntity implements Serializable, Appointable {

	private static final long serialVersionUID = -3164713918774455925L;

	@Id
	@Column(name="EMP_ID")
	private String id;
	
	@Column(name="EMP_NAME")
	private String name;
		
	@Column(name="RREGNO")
	private String residentRegistrationNumber;
		
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<DeptChangeHistory> deptHistory = new ArrayList<>();
	
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	private List<JobChangeHistory> jobHistory = new ArrayList<>();
	
	public Employee(String id, String name, String residentRegistrationNumber) {
		this.id = id;
		this.name = name;
		this.residentRegistrationNumber = residentRegistrationNumber;
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
