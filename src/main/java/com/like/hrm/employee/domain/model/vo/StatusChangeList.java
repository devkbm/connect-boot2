package com.like.hrm.employee.domain.model.vo;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.like.hrm.employee.domain.model.StatusChangeHistory;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class StatusChangeList {

	/**
	 * 근무상태 이력
	 */
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<StatusChangeHistory> statusHistory = new LinkedHashSet<>();
	
	public Set<StatusChangeHistory> getStatusChangeHistory() {
		return this.statusHistory;
	}
			
	public void add(StatusChangeHistory newHistory) {
		LocalDate newFromDate = newHistory.getPeriod().getFrom();
		StatusChangeHistory oldHistory = this.getStatusChangeHistory(newFromDate);
		
		if (isValid(newFromDate)) {
			throw new IllegalArgumentException(newHistory.getPeriod().getFrom() + "이전 이력이 존재합니다.");
		}
		
		if (oldHistory == null) {
			this.statusHistory.add(newHistory);			
		} else {
			if (oldHistory.getStatusCode() == newHistory.getStatusCode()) {
				// 동일한 근무상태일 경우 일자 체크 로직 추가해야함
				
			} else if (oldHistory.getStatusCode() != newHistory.getStatusCode()) {
				oldHistory.expire(newFromDate.minusDays(1));
				this.statusHistory.add(newHistory);
			}		
		}			
		
	}
	
	private StatusChangeHistory getStatusChangeHistory(LocalDate date) {	
		StatusChangeHistory rtn = null;		
		
		for (StatusChangeHistory statusHistory: this.getStatusChangeHistory()) {
			if (statusHistory.isEnabled(date)) {
				rtn = statusHistory;
			}
		}
		
		return rtn;			
	}
		
	
	private boolean isValid(LocalDate referenceDate) {
		boolean rtn = true;
		
		for (StatusChangeHistory statusHistory: this.getStatusChangeHistory()) {
			if (referenceDate.isBefore(statusHistory.getPeriod().getFrom()))
				rtn = false;
		}
		
		return rtn;
	}
}
