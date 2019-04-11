package com.like.team.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.team.domain.model.TeamMember;

@Repository
public interface JpaTeamMember extends JpaRepository<TeamMember, Long> {

}
