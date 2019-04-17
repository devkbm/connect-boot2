package com.like.workschedule.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.workschedule.domain.model.WorkGroup;

@Repository
public interface JpaWorkGroup extends JpaRepository<WorkGroup, Long> {

}
