package com.like.hrm.employee.domain.repository;

import java.time.LocalDate;


import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.like.dept.domain.model.QDept;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.QDeptChangeHistory;
import com.like.hrm.employee.domain.model.QEmployee;

public class EmployeeExpression {

	/**
	 * 현재 사용중인 부서만 검색
	 * @return
	 */
	@QueryDelegate(Employee.class)
	public static BooleanExpression containCurrentDept(QEmployee employee) {
		
		DateExpression<LocalDate> now = DateExpression.currentDate(LocalDate.class);
		QDeptChangeHistory qDeptChangeHistory = QDeptChangeHistory.deptChangeHistory;
		
		//return now.between(employee.deptHistory..fromDate, code.toDate);
		return JPAExpressions.select(Expressions.constant(1))
				  			 .from(qDeptChangeHistory)
				             .where(now.between(qDeptChangeHistory.fromDate, qDeptChangeHistory.toDate)).exists();
	}
	
	@QueryDelegate(Employee.class)
	public static BooleanExpression equalDeptCode(QEmployee employee, String deptCode) {
		
		QDeptChangeHistory qDeptChangeHistory = QDeptChangeHistory.deptChangeHistory;
		
		return JPAExpressions.select(Expressions.constant(1))
				  			 .from(qDeptChangeHistory)
				             .where(qDeptChangeHistory.deptCode.eq(deptCode)).exists();
	}
	
	/**
	 * jpql에서 key가 아닌 컬럼 조인 어떻게 해야하는지 검색필요
	 * @param employee
	 * @param deptName
	 * @return
	 */
	@QueryDelegate(Employee.class)
	public static BooleanExpression equalDeptName(QEmployee employee, String deptName) {
		
		QDeptChangeHistory qDeptChangeHistory = QDeptChangeHistory.deptChangeHistory;
		QDept qDept = QDept.dept;
		
		return JPAExpressions.select(Expressions.constant(1))
				  			 .from(qDeptChangeHistory)				  			
				  			 //.innerJoin(qDeptChangeHistory.deptCode, qDept)
				             .where(qDeptChangeHistory.deptCode.like("%"+deptName+"%")).exists();
	}
	
	/*
	@QueryDelegate(Code.class)
	public static BooleanExpression isRootNode(QCode code) {							
		return code.parentCode.isNull();
	}
	
	@QueryDelegate(Code.class)
	public static BooleanExpression isLeafNode(QCode code) {							
		return code.parentCode.isNotNull();
	}
	*/
}
