package com.like.workschedule.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.workschedule.domain.model.WorkGroupMember;
import com.like.workschedule.domain.model.id.WorkGroupMemberId;

@Repository
public interface JpaWorkGroupMember extends JpaRepository<WorkGroupMember, WorkGroupMemberId>, QuerydslPredicateExecutor<WorkGroupMember> { 

}
