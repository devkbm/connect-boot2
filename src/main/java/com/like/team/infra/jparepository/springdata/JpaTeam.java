package com.like.team.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.team.domain.model.Team;

@Repository
public interface JpaTeam extends JpaRepository<Team, Long>, QuerydslPredicateExecutor<Team> {

}
