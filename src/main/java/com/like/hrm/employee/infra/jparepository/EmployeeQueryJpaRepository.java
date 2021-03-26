package com.like.hrm.employee.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.hrm.employee.boundary.EmployeeDTO.SearchEmployee;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.QDeptChangeHistory;
import com.like.hrm.employee.domain.model.QEmployee;
import com.like.hrm.employee.domain.model.QJobChangeHistory;
import com.like.hrm.employee.domain.model.QLicense;
import com.like.hrm.employee.domain.model.QSchoolCareer;
import com.like.hrm.employee.domain.model.QStatusChangeHistory;
import com.like.hrm.employee.domain.repository.EmployeeQueryRepository;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class EmployeeQueryJpaRepository implements EmployeeQueryRepository {

	private JPAQueryFactory queryFactory;
	private static final QEmployee qEmployee = QEmployee.employee;
	private static final QDeptChangeHistory qDeptChangeHistory = QDeptChangeHistory.deptChangeHistory;
	private static final QJobChangeHistory qJobChangeHistory = QJobChangeHistory.jobChangeHistory;
	private static final QStatusChangeHistory qStatusChangeHistory = QStatusChangeHistory.statusChangeHistory;
	private static final QSchoolCareer qSchoolCareer = QSchoolCareer.schoolCareer;
	private static final QLicense qLicense = QLicense.license;
	
	public EmployeeQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}

	@Override
	public List<Employee> getEmployeeList(SearchEmployee dto) {
		return queryFactory
				.select(qEmployee).distinct()
				.from(qEmployee)
				.leftJoin(qEmployee.deptHistory.deptHistory, qDeptChangeHistory)
				.fetchJoin()
				.leftJoin(qEmployee.jobHistory.jobHistory, qJobChangeHistory)
				.fetchJoin()
				.leftJoin(qEmployee.statusHistory.statusHistory, qStatusChangeHistory)
				.fetchJoin()
				.leftJoin(qEmployee.schoolCareerList.schoolCareerList, qSchoolCareer)
				.fetchJoin()
				.leftJoin(qEmployee.licenseList.licenseList, qLicense)
				.fetchJoin()
				.where(dto.getBooleanBuilder())				
				.fetch();
	}

	@Override
	public Employee getLastEmployee(String yyyy) {
		return queryFactory.select(qEmployee)
				.from(qEmployee)					
				.where(qEmployee.id.eq(JPAExpressions
										.select(qEmployee.id.max())
										.from(qEmployee)
										.where(qEmployee.id.like(yyyy+'%'))))													
				.fetchOne();
	}
}
