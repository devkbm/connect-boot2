package com.like.hrm.employee.domain.repository;

import java.time.LocalDate;
import java.util.List;

import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.like.dept.domain.model.QDept;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.QDeptChangeHistory;
import com.like.hrm.employee.domain.model.QEducation;
import com.like.hrm.employee.domain.model.QEmployee;
import com.like.hrm.employee.domain.model.QJobChangeHistory;
import com.like.hrm.employee.domain.model.QLicense;
import com.like.hrm.employee.domain.model.QStatusChangeHistory;

public class EmployeeExpression {

		
	@QueryDelegate(Employee.class)
	public static BooleanExpression referenceDate(QEmployee employee, LocalDate referenceDate) {
		//DateExpression<LocalDate> now = DateExpression.currentDate(LocalDate.class);
		DateExpression<LocalDate> date = Expressions.asDate(referenceDate);
		
		QDeptChangeHistory qDeptChangeHistory = QDeptChangeHistory.deptChangeHistory;
		QJobChangeHistory qJobChangeHistory = QJobChangeHistory.jobChangeHistory;
		QStatusChangeHistory qStatusChangeHistory = QStatusChangeHistory.statusChangeHistory;				
		
		return date.between(qDeptChangeHistory.fromDate, qDeptChangeHistory.toDate)
		  .and(date.between(qJobChangeHistory.fromDate, qJobChangeHistory.toDate))
		  .and(date.between(qStatusChangeHistory.fromDate, qStatusChangeHistory.toDate));
		  
	}
	
	@QueryDelegate(Employee.class)
	public static BooleanExpression equalDeptCode(QEmployee employee, String deptType, String deptCode) {		
		
		QDeptChangeHistory qDeptChangeHistory = QDeptChangeHistory.deptChangeHistory;				
		
		return qDeptChangeHistory.deptType.eq(deptType)
		  .and(qDeptChangeHistory.deptCode.eq(deptCode));			
	}
	
	@QueryDelegate(Employee.class)
	public static BooleanExpression inDeptCode(QEmployee employee, String deptType, List<String> deptCode) {		
		
		QDeptChangeHistory qDeptChangeHistory = QDeptChangeHistory.deptChangeHistory;				
		
		return qDeptChangeHistory.deptType.eq(deptType)
		  .and(qDeptChangeHistory.deptCode.in(deptCode));			
	}
	
	
	
	@QueryDelegate(Employee.class)
	public static BooleanExpression equalJobCode(QEmployee employee, String jobType, String jobCode) {		
		
		QJobChangeHistory qJobChangeHistory = QJobChangeHistory.jobChangeHistory;				
		
		return qJobChangeHistory.jobType.eq(jobType)
		  .and(qJobChangeHistory.jobCode.eq(jobCode));			
	}
		
	@QueryDelegate(Employee.class)
	public static BooleanExpression likeDeptName(QEmployee employee, String deptName, LocalDate date) {
		
		QDeptChangeHistory qDeptChangeHistory = QDeptChangeHistory.deptChangeHistory;
		DateExpression<LocalDate> dateExpression = Expressions.asDate(date);
		QDept qDept = QDept.dept;
		
		return JPAExpressions.select(Expressions.constant(1))
				  			 .from(qDeptChangeHistory)				  			
				  			 .join(qDept)
				  			 .on(qDeptChangeHistory.deptCode.eq(qDept.deptCode))				  			 				  			 				  		
				             .where(qDept.deptNameKorean.like(deptName)
				               .and(qDeptChangeHistory.employee.eq(employee))
				               .and(dateExpression.between(qDeptChangeHistory.fromDate, qDeptChangeHistory.toDate)))
				             .exists();
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
