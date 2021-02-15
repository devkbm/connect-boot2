package com.like.team.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.team.domain.model.TeamMember;
import com.like.user.domain.model.User;
import com.like.team.boundary.TeamDTO;
import com.like.team.domain.model.Team;

@Repository
public interface TeamRepository {

	Team getTeam(Long teamId);
	
	List<Team> getTeamList(TeamDTO.SearchCondition searchCondition);
	
	void saveTeam(Team team);
	
	void deleteTeam(Team team);
			
	TeamMember getTeamMember(Team team, User member);	
	
	void saveJoinTeam(TeamMember joinTeam);
	
	void saveJoinTeam(List<TeamMember> teamMemberList);
	
	void deleteJoinTeam(TeamMember joinTeam);
	
	void deleteJoinTeam(List<TeamMember> teamMemberList);
	
}
