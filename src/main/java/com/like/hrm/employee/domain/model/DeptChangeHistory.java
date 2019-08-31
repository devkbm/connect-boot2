package com.like.hrm.employee.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

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
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMEMPDEPTHISTORY")
@EntityListeners(AuditingEntityListener.class)
public class DeptChangeHistory extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -1910423657477232102L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
		
	/**
	 * 부서유형
	 */
	@Column(name="DEPT_TYPE")
	private String deptType;
	
	/**
	 * 부서코드
	 */
	@Column(name="DEPT_CODE")
	private String deptCode;
	
	/**
	 * 시작일
	 */
	@Column(name="FROM_DT")
	private LocalDate fromDate;
	
	/**
	 * 종료일
	 */
	@Column(name="TO_DT")
	private LocalDate toDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;
	
	public DeptChangeHistory(Employee employee, String deptType, String deptCode, LocalDate fromDate, LocalDate toDate) {
		this.employee = employee;
		this.deptType = deptType;
		this.deptCode = deptCode;
		this.fromDate = fromDate;
		this.toDate = toDate;		
	}
	
	/**
	 * 기준일에 사용가능한지 여부 리턴
	 * @param date 기준일
	 * @return
	 */
	public boolean isEnabled(LocalDate date) {		
		return ( date.isAfter(fromDate) || date.isEqual(fromDate) ) 
		 	&& ( date.isBefore(toDate) || date.isEqual(toDate) ) ? true : false;		
	}
	
	/**
	 * 부서이력의 종료시킨다
	 * 예외) 종료일이 시작일보다 이전일 경우 시작일로 변경
	 * @param date 종료일
	 */
	public void terminateHistory(LocalDate date) {
		if (date.isAfter(this.fromDate)) {
			this.toDate = date;
		} else {
			this.toDate = this.fromDate;
		}
	}
	
	public boolean equalDeptHistory(Employee employee, String deptType, String deptCode) {
		boolean rtn = false;
		
		if ( this.employee.equals(employee) 
		  && this.deptType.equals(deptType) 
		  && this.deptCode.equals(deptCode) ) {
			rtn = true;
		}
		
		return rtn;
	}
	
	public boolean equalDeptType(String deptType) {
		return this.deptType.equals(deptType) ? true : false;
	}
	
	public boolean equalDeptCode(String deptCode) {
		return this.deptCode.equals(deptCode) ? true : false;
	}
	
	
			
}
