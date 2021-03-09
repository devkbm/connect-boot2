package com.like.hrm.employee.domain.model.vo;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.like.hrm.employee.domain.model.JobChangeHistory;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class JobChangeList {

	/**
	 * 직위 직급 등 인사정보 이력
	 */
	@OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	Set<JobChangeHistory> jobHistory = new LinkedHashSet<>();
	
	public Set<JobChangeHistory> getJobChangeHistory() {
		return this.jobHistory;
	}
	
	/**
	 * <p>인사정보이력을 추가한다.</p>
	 * 1. 동일 인사 유형의 유효한 인사 정보 종료
	 * 2. 신규 인사 정보 입력
	 * @param jobChangeHistory
	 */
	public void add(JobChangeHistory newHistory) {
		LocalDate newFromDate = newHistory.getPeriod().getFrom();
		JobChangeHistory oldHistory = this.getJobChangeHistory(newFromDate);
		/*
		if (isValid(newFromDate)) {
			throw new IllegalArgumentException(newHistory.getPeriod().getFrom() + "이전 이력이 존재합니다.");
		}
		*/
		
		addHistory(oldHistory, newHistory);
	}
	
	private void addHistory(JobChangeHistory oldHistory, JobChangeHistory newHistory) {
		LocalDate newFromDate = newHistory.getPeriod().getFrom();
				
		if (oldHistory == null) {
			this.jobHistory.add(newHistory);			
		} else {
			if (oldHistory.equal(newHistory.getJobType(), newHistory.getJobCode())) {
				if (newHistory.getPeriod().getTo().isBefore(oldHistory.getPeriod().getTo())) {
					oldHistory.expire(newHistory.getPeriod().getTo());
				}
			} else if (oldHistory.equal(newHistory.getJobType(), newHistory.getJobCode()) != true) {
				oldHistory.expire(newFromDate.minusDays(1));
				this.jobHistory.add(newHistory);
			}		
		}
	}	
	
	private JobChangeHistory getJobChangeHistory(LocalDate date) {
		JobChangeHistory history = null;
		
		for (JobChangeHistory jobHistory: this.getJobChangeHistory()) {
			if (jobHistory.isEnabled(date)) {
				history = jobHistory;
			}
				
		}
		
		return history;
	}
	
	private boolean isValid(LocalDate referenceDate) {
		boolean rtn = true;
		
		for (JobChangeHistory jobHistory: this.getJobChangeHistory()) {
			if (referenceDate.isBefore(jobHistory.getPeriod().getFrom()))
				rtn = false;
		}
		
		return rtn;
	}
}
