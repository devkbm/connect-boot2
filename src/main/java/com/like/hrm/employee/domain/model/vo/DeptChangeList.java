package com.like.hrm.employee.domain.model.vo;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.like.hrm.employee.domain.model.DeptChangeHistory;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class DeptChangeList {

	/**
	 * 부서이력
	 */
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<DeptChangeHistory> deptHistory = new LinkedHashSet<>();
		
	public Set<DeptChangeHistory> getDeptChangeHistory() {
		return this.deptHistory;
	}
	
	/**
	 * <p>부서변경이력을 추가한다.</p>
	 * @param newHistory
	 */
	public void add(DeptChangeHistory newHistory) {
		LocalDate newFromDate = newHistory.getPeriod().getFrom();
		DeptChangeHistory oldHistory = this.getDeptChangeHistory(newHistory.getDeptType(), newFromDate);
		
		/*
		if (isValid(newFromDate)) {
			throw new IllegalArgumentException(newHistory.getPeriod().getFrom() + "이전 이력이 존재합니다.");
		}
		*/
		
		addHistory(oldHistory, newHistory);				
	}
	
	private void addHistory(DeptChangeHistory oldHistory, DeptChangeHistory newHistory) {
					
		// 기존 부서이력이 없을 경우
		if (oldHistory == null) {
			this.deptHistory.add(newHistory);			
		} 
		// 기존 부서이력이 있을 경우 (동일 부서구분)  
		else if (oldHistory.equalDeptType(newHistory.getDeptType())) {

			LocalDate oldToDate = oldHistory.getPeriod().getTo();
			LocalDate newToDate = newHistory.getPeriod().getTo();
			
			// 동일부서코드일 경우 기존 이력 날짜 조정
			if (oldHistory.equalDeptCode(newHistory.getDeptCode())) {
				// 기존 이력에 신규 종료일 적용  
				if (!newToDate.isEqual(oldToDate)) {
					oldHistory.expire(newToDate);
				}
			}
			// 동일부서코드가 아닐 경우 기존 이력 종료 후 신규이력 등록
			else {
				LocalDate newFromDate = newHistory.getPeriod().getFrom();
				
				oldHistory.expire(newFromDate.minusDays(1));
				this.deptHistory.add(newHistory);
			}											
		}
	}
		
	private DeptChangeHistory getDeptChangeHistory(String deptType, LocalDate date) {
		DeptChangeHistory history = null;
		
		for (DeptChangeHistory deptHistory: this.getDeptChangeHistory()) {
			if (deptHistory.equalDeptType(deptType) && deptHistory.isEnabled(date)) {
				history = deptHistory;
			}
				
		}
		
		return history;
	}
	
	private boolean isValid(LocalDate referenceDate) {
		boolean rtn = true;
		
		for (DeptChangeHistory deptHistory: this.getDeptChangeHistory()) {
			if (referenceDate.isBefore(deptHistory.getPeriod().getFrom()))
				rtn = false;
		}
		
		return rtn;
	}
}
