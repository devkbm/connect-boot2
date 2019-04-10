package com.like.todo.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.todo.domain.model.Task;

@Repository
public interface JpaTask extends JpaRepository<Task,Long> {

}
