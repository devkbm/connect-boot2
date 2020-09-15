package com.like.hrm.employee.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

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

import org.hibernate.annotations.Formula;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;
import com.like.common.vo.DatePeriod;

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
	
	@Formula("(select x.DEPT_NM_KOR from com.comdept x where x.dept_cd = dept_code)")
	private String deptName;
		
	@Embedded
	private DatePeriod period;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_ID", nullable=false, updatable=false)
	private Employee employee;
	
	public DeptChangeHistory(Employee employee, String deptType, String deptCode, DatePeriod period) {
		this.employee = employee;
		this.deptType = deptType;
		this.deptCode = deptCode;		
		this.period = period;
	}
	
	/**
	 * 기준일에 사용가능한지 여부 리턴
	 * @param date 기준일
	 * @return
	 */
	public boolean isEnabled(LocalDate date) {		
		return ( date.isAfter(this.period.getFrom()) || date.isEqual(this.period.getFrom()) ) 
		 	&& ( date.isBefore(this.period.getTo()) || date.isEqual(this.period.getTo()) ) ? true : false;		
	}
	
	/**
	 * 부서이력의 종료시킨다
	 * 예외) 종료일이 시작일보다 이전일 경우 시작일로 변경
	 * @param date 종료일
	 */
	public void expire(LocalDate date) {
		if (date.isAfter(this.period.getFrom())) {
			this.period = new DatePeriod(this.period.getFrom(), date);
		} else {
			this.period = new DatePeriod(this.period.getFrom(), this.period.getFrom());			
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
