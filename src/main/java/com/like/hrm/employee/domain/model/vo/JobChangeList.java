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
	public void add(JobChangeHistory jobChangeHistory) {
		this.expire(jobChangeHistory.getJobType()
						 ,jobChangeHistory.getPeriod().getFrom());
		
		jobHistory.add(jobChangeHistory);
	}
	
	/**
	 * <p>인사정보 이력을 종료일자 기준으로 종료시킨다.</p>
	 * @param jobType
	 * @param referenceDate
	 */
	private void expire(String jobType, LocalDate referenceDate) {
		
		for (JobChangeHistory jobHistory: this.getJobChangeHistory()) {
			
			if (jobHistory.equalJobType(jobType) 
			 && referenceDate.isBefore(jobHistory.getPeriod().getFrom())) {
				throw new IllegalArgumentException(jobHistory.getPeriod().getFrom() + "일로 시작하는 이력이 존재합니다.");
			}
			
			if ( jobHistory.equalJobType(jobType) 			  
			  && jobHistory.isEnabled(referenceDate) )
				
				jobHistory.expire(referenceDate.minusDays(1));				
		}
	}
}
