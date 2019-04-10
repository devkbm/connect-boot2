package com.like.todo.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.todo.domain.model.TaskGroup;

@Repository
public interface JpaTaskGroup extends JpaRepository<TaskGroup,Long> {

}
