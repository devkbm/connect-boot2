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
		
	public void add(StatusChangeHistory statusChangeHistory) {
		expire(statusChangeHistory.getPeriod().getFrom());
					
		this.statusHistory.add(statusChangeHistory);
	}
	
	public void expire(LocalDate date) {
		for (StatusChangeHistory statusHistory: this.getStatusChangeHistory()) {
			if (isValid(statusHistory, date)) {
				throw new IllegalArgumentException(statusHistory.getPeriod().getFrom() + "일로 시작하는 이력이 존재합니다.");
			}
			
			if (statusHistory.isEnabled(date)) {
				statusHistory.expire(date.minusDays(1));
			}
		}		
	}
	
	private boolean isValid(StatusChangeHistory statusHistory, LocalDate referenceDate) {
		return referenceDate.isBefore(statusHistory.getPeriod().getFrom());
	}
}
