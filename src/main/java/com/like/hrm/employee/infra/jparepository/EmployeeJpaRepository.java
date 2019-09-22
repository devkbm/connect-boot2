package com.like.hrm.employee.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.QEmployee;
import com.like.hrm.employee.domain.repository.EmployeeRepository;
import com.like.hrm.employee.infra.jparepository.springdata.JpaEmployee;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class EmployeeJpaRepository implements EmployeeRepository {

	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaEmployee jpaEmployee;
	
	private static final QEmployee qEmployee = QEmployee.employee;
	
	@Override
	public Employee getEmployee(String id) {
		Optional<Employee> entity = jpaEmployee.findById(id);
		
		return entity.orElse(null);
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

	@Override
	public List<Employee> getEmployeeList() {
		
		return jpaEmployee.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return jpaEmployee.save(employee);
	}

	@Override
	public void deleteEmployee(String id) {
		jpaEmployee.deleteById(id);
	}

}
