package com.like.team.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.like.team.domain.model.TeamMember;
import com.like.team.domain.model.Team;
import com.like.team.domain.repository.TeamRepository;
import com.like.team.dto.TeamDTO;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.like.user.dto.UserDTO;
import com.like.user.service.UserService;

@Service
@Transactional
public class TeamService {

	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	UserService userService;
	
	public Team getTeam(Long teamId) {
		return teamRepository.getTeam(teamId);
	}
	
	public List<Team> getTeamList(TeamDTO.SearchCondition searchCondition) {
		return teamRepository.getTeamList(searchCondition);
	}
	
	public void saveTeam(Team team) {
		teamRepository.saveTeam(team);
	}
	
	public void deleteTeam(Team team) {
		teamRepository.deleteTeam(team);
	}
	
	public List<User> getAllMember(UserDTO.QueryCondition searchCondition) {
		return userService.getUserList(searchCondition);
	}
	
	public List<User> getTeamMemberList(Long teamId) {
		Team team = teamRepository.getTeam(teamId);
		
		return team.getUserList();
	}
					
	public TeamMember getTeamMember(Long id) {
		return teamRepository.getTeamMember(id);
	}
	
	public TeamMember joinTeam(Long teamId, String userId) {
		Team team = teamRepository.getTeam(teamId);
		User member = userService.getUser(userId);
		
		TeamMember joinTeam = new TeamMember(team, member);
		
		teamRepository.saveJoinTeam(joinTeam);
		
		return joinTeam;
	}	
	
	public void quitTeam(String memberId, String teamId) {
		TeamMember joinTeam = null; //teamRepository.getJoinTeam(teamId, memberId);
		
		teamRepository.deleteJoinTeam(joinTeam);
	}
	
}
