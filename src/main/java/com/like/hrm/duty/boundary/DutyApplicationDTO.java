package com.like.hrm.duty.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.hrm.duty.domain.model.QDutyApplication;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class DutyApplicationDTO {

	public static class SearchDutyApplication implements Serializable {
		
		private static final long serialVersionUID = 6850895780318962483L;
		
		private final QDutyApplication qDutyApplication = QDutyApplication.dutyApplication;
		
		String employeeId;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqEmployeeId(this.employeeId));
			
			return builder;
		}
		
		private BooleanExpression eqEmployeeId(String employeeId) {
			if (StringUtils.isEmpty(employeeId)) {
				return null;
			}
			
			return qDutyApplication.employeeId.eq(employeeId);
		}
		
	}
}
