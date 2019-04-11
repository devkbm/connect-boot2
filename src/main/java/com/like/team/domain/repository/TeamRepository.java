package com.like.team.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.team.domain.model.TeamMember;
import com.like.team.dto.TeamDTO;
import com.like.team.domain.model.Team;

@Repository
public interface TeamRepository {

	Team getTeam(Long teamId);
	
	List<Team> getTeamList(TeamDTO.SearchCondition searchCondition);
	
	void saveTeam(Team team);
	
	void deleteTeam(Team team);
	
	TeamMember getTeamMember(Long id);
			
	TeamMember getTeamMember(String teamId, String memberId);
	
	void saveJoinTeam(TeamMember joinTeam);
	
	void deleteJoinTeam(TeamMember joinTeam);
	
}
