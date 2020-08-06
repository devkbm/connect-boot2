package com.like.hrm.duty.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.hrm.duty.domain.model.QDutyCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

public class DutyCodeDTO {

	public static class SearchDutyCode implements Serializable {
			
		private static final long serialVersionUID = 393877591925132395L;

		private final QDutyCode qDutyCode = QDutyCode.dutyCode1; 
		
		String dutyCode;
		
		String dutyName;
		
		Boolean enabled;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			builder
				.and(likeDutyCode(this.dutyCode))
				.and(likeDutyName(this.dutyName));
			
			return builder;
		}
		
		private BooleanExpression likeDutyCode(String dutyCode) {
			if (StringUtils.isEmpty(dutyCode)) {
				return null;
			}
			
			return qDutyCode.dutyCode.like("%"+dutyCode+"%");
		}
		
		private BooleanExpression likeDutyName(String dutyName) {
			if (StringUtils.isEmpty(dutyName)) {
				return null;
			}
			
			return qDutyCode.dutyName.like("%"+dutyName+"%");
		}
	}
}
