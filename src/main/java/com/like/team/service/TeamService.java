package com.like.team.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.team.domain.model.TeamMember;
import com.like.team.domain.model.id.TeamMemberId;
import com.like.team.boundary.TeamDTO;
import com.like.team.domain.model.Team;
import com.like.team.domain.repository.TeamRepository;
import com.like.user.boundary.UserDTO;
import com.like.user.domain.model.User;
import com.like.user.service.UserQueryService;
import com.like.user.service.UserService;

@Service
@Transactional
public class TeamService {
	
	private TeamRepository teamRepository;		
	private UserService userService;
	private UserQueryService userQueryService;
	
	public TeamService(TeamRepository teamRepository
					  ,UserService userService
					  ,UserQueryService userQueryService) {
		this.teamRepository = teamRepository;
		this.userService = userService;
		this.userQueryService = userQueryService;
	}
	
	public Team getTeam(Long teamId) {
		return teamRepository.getTeam(teamId);
	}
	
	/**
	 * 조건에 해당하는 팀 명단을 조회한다.
	 * @param searchCondition 조회조건
	 * @return List<Team> 팀 명단
	 */
	public List<Team> getTeamList(TeamDTO.SearchCondition searchCondition) {
		return teamRepository.getTeamList(searchCondition);
	}
	
	/**
	 * 팀을 저장한다.
	 * @param team 팀 엔티티
	 * @param teamMemberList 팀원 엔티티
	 */
	public void saveTeam(Team team, List<User> userList) {
		teamRepository.saveTeam(team);
					
		// 기존 등록된 멤버 삭제
		this.clearTeamMemberList(team);
		
		if (userList != null) {
		
			List<TeamMember> teamMemberList = new ArrayList<TeamMember>();		
			for (User user: userList) {
				teamMemberList.add(new TeamMember(team, user));
			}	
			
			// 팀원 등록
			teamRepository.saveJoinTeam(teamMemberList);
		}
	}
	
	/**
	 * 팀을 삭제한다.
	 * @param team 팀 엔티티
	 */
	public void deleteTeam(Team team) {
		teamRepository.deleteTeam(team);
	}
	
	/**
	 * 조건에 해당하는 유저 정보를 조회한다.
	 * @param searchCondition 조회 조건
	 * @return User 
	 */
	public List<User> getAllMember(UserDTO.SearchUser searchCondition) {
		return userQueryService.getUserList(searchCondition);
	}
	
	/**
	 * 팀에 소속된 팀원 명단을 조회한다.
	 * @param teamId
	 * @return
	 */
	public List<User> getTeamMemberList(Long teamId) {
		Team team = teamRepository.getTeam(teamId);
		
		return team.getMemberList();
	}
					
	/**
	 * 
	 * @param teamId 팀 엔티티 Id
	 * @param userId 유저 엔티티 Id
	 * @return 
	 */
	public TeamMember getTeamMember(Long teamId, String userId) {
		Team team = teamRepository.getTeam(teamId);
		User user = userService.getUser(userId);
				
		return teamRepository.getTeamMember(team, user);
	}
	
	/**
	 * 팀에 가입한다.
	 * @param teamId 팀 엔티티 Id
	 * @param userId 유저 엔티티 Id
	 * @return 
	 */
	public TeamMember joinTeam(Long teamId, String userId) {
		//Team team = teamRepository.getTeam(teamId);
		//User member = userService.getUser(userId);
		
		TeamMember joinTeam = new TeamMember(new TeamMemberId(teamId, userId));
		
		teamRepository.saveJoinTeam(joinTeam);
		
		return joinTeam;
	}	
		
	/**
	 * 팀에 해당하는 팀원 정보를 초기화한다.
	 * @param team 팀 엔티티
	 */
	public void clearTeamMemberList(Team team) {
		List<TeamMember> teamMemberList = team.getTeamMemberList();
		
		teamRepository.deleteJoinTeam(teamMemberList);
	}	
	
}
