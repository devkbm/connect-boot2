package com.like.dept.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.dept.domain.model.QDept;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Data;

public class SearchCondition {

	@Data
	public static class DeptSearch implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QDept qDept = QDept.dept;
		
		String deptCode;
				
		String deptName;
					
		Boolean isEnabled;
		
		public BooleanBuilder getCondition() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeDeptCode(this.deptCode))
				.and(likeDeptName(this.deptCode));
														
			return builder;
		}
		
		private BooleanExpression likeDeptCode(String deptCode) {
			if (StringUtils.isEmpty(deptCode)) {
				return null;
			}
			
			return qDept.deptCode.like("%"+deptCode+"%");
		}
		
		private BooleanExpression likeDeptName(String deptName) {
			if (StringUtils.isEmpty(deptCode)) {
				return null;
			}
			
			return qDept.deptNameKorean.like("%"+deptName+"%");
		}
	}
}
