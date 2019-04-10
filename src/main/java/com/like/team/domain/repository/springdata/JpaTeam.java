package com.like.team.domain.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.team.domain.model.Team;

@Repository
public interface JpaTeam extends JpaRepository<Team, String> {

}
