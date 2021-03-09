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
		DeptChangeHistory oldHistory = this.getDeptChangeHistory(newFromDate);
		
		if (isValid(newFromDate)) {
			throw new IllegalArgumentException(newHistory.getPeriod().getFrom() + "이전 이력이 존재합니다.");
		}
		
		addHistory(oldHistory, newHistory);				
	}
	
	private void addHistory(DeptChangeHistory oldHistory, DeptChangeHistory newHistory) {
		LocalDate newFromDate = newHistory.getPeriod().getFrom();
				
		if (oldHistory == null) {
			this.deptHistory.add(newHistory);			
		} else {
			if (oldHistory.equal(newHistory.getDeptType(), newHistory.getDeptCode())) {
				if (newHistory.getPeriod().getTo().isBefore(oldHistory.getPeriod().getTo())) {
					oldHistory.expire(newHistory.getPeriod().getTo());
				}
			} else if (oldHistory.equal(newHistory.getDeptType(), newHistory.getDeptCode()) != true) {
				oldHistory.expire(newFromDate.minusDays(1));
				this.deptHistory.add(newHistory);
			}		
		}
	}
		
	private DeptChangeHistory getDeptChangeHistory(LocalDate date) {
		DeptChangeHistory history = null;
		
		for (DeptChangeHistory deptHistory: this.getDeptChangeHistory()) {
			if (deptHistory.isEnabled(date)) {
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
