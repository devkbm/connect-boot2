package com.like.hrm.employee.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.hrm.employee.domain.model.Employee;

@Repository
public interface JpaEmployee extends JpaRepository<Employee, String> {

}
