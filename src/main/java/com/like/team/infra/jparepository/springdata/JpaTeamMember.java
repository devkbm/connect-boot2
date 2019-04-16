package com.like.team.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.team.domain.model.TeamMember;
import com.like.team.domain.model.id.TeamMemberId;

@Repository
public interface JpaTeamMember extends JpaRepository<TeamMember, TeamMemberId> {

}
