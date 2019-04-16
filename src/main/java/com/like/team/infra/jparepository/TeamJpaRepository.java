package com.like.team.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.team.domain.model.TeamMember;
import com.like.team.domain.model.id.TeamMemberId;
import com.google.common.collect.Lists;
import com.like.team.domain.model.Team;
import com.like.team.domain.repository.TeamRepository;
import com.like.team.dto.TeamDTO;
import com.like.team.infra.jparepository.springdata.JpaTeam;
import com.like.team.infra.jparepository.springdata.JpaTeamMember;
import com.like.user.domain.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TeamJpaRepository implements TeamRepository {

	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaTeam jpaTeam;
			
	@Autowired
	private JpaTeamMember jpaTeamMember;

	//private static final QTeamMember qTeamMember;
	
	@Override
	public Team getTeam(Long teamId) {
		Optional<Team> entity = jpaTeam.findById(teamId); 
				
		return entity.isPresent() ? entity.get() : null;
	}

	@Override
	public List<Team> getTeamList(TeamDTO.SearchCondition searchCondition) {			
		return Lists.newArrayList(jpaTeam.findAll(searchCondition.getCondition()));
	}

	@Override
	public void saveTeam(Team team) {
		jpaTeam.save(team);
		//jpaTeam.saveAndFlush(team);		
	}

	@Override
	public void deleteTeam(Team team) {
		jpaTeam.delete(team);		
	}
	

	@Override
	public TeamMember getTeamMember(Team team, User member) {				
		Optional<TeamMember> entity = jpaTeamMember.findById(new TeamMemberId(team.getTeamId(), member.getUserId()));
		
		return entity.isPresent() ? entity.get() : null;
	}

	@Override
	public void saveJoinTeam(TeamMember joinTeam) {
		jpaTeamMember.save(joinTeam);		
	}
	
	@Override
	public void saveJoinTeam(List<TeamMember> teamMemberList) {
		jpaTeamMember.saveAll(teamMemberList);		
	}

	@Override
	public void deleteJoinTeam(TeamMember joinTeam) {
		jpaTeamMember.delete(joinTeam);
	}

	@Override
	public void deleteJoinTeam(List<TeamMember> teamMemberList) {
		jpaTeamMember.deleteAll(teamMemberList);		
	}
	
}
