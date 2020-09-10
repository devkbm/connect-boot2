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
	 * 1. 동일 부서 유형의 유효한 부서 정보 종료
	 * 2. 신규 부서 정보 입력
	 * @param deptChangeHistory
	 */
	public void add(DeptChangeHistory deptChangeHistory) {
		
		this.expire(deptChangeHistory.getDeptType()
						  ,deptChangeHistory.getPeriod().getFrom());
		
		deptHistory.add(deptChangeHistory);
	}
	
	/**
	 * <p>부서 이력 중 기준일자에 해당하는 사용가능한 이력이 있을 경우 전일로 종료시킨다.</p>
	 * @param deptType
	 * @param referenceDate
	 */
	private void expire(String deptType, LocalDate referenceDate) {
				
		for (DeptChangeHistory deptHistory: this.getDeptChangeHistory()) {
			
			if (referenceDate.isBefore(deptHistory.getPeriod().getFrom())
			 && deptHistory.equalDeptType(deptType)) {
				throw new IllegalArgumentException(deptHistory.getPeriod().getFrom() + "일로 시작하는 부서 이력이 존재합니다.");
			}
			
			if ( deptHistory.equalDeptType(deptType)			  	
			  && deptHistory.isEnabled(referenceDate) )
				
				deptHistory.expire(referenceDate.minusDays(1));				
		}									
		
	}
}
