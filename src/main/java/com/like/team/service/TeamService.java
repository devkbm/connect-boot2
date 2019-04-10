package com.like.team.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.like.team.domain.model.JoinTeam;
import com.like.team.domain.model.Member;
import com.like.team.domain.model.Team;
import com.like.team.domain.repository.TeamRepository;

@Service
@Transactional
public class TeamService {

	@Autowired
	TeamRepository teamRepository;
	
	public Team getTeam(String teamId) {
		return teamRepository.getTeam(teamId);
	}
	
	public List<Team> getTeamList() {
		return teamRepository.getTeamList();
	}
	
	public void saveTeam(Team team) {
		teamRepository.saveTeam(team);
	}
	
	public void deleteTeam(Team team) {
		teamRepository.deleteTeam(team);
	}
	
	public List<Member> getMemberList(String teamId) {
		Team team = teamRepository.getTeam(teamId);
		
		return team.getMemberList();
	}
	
	public List<Team> getTeamList(String memberId) {
		Member member = teamRepository.getMember(memberId);
		
		return member.getTeamList();
	}
	
	public Member getMember(String memberId) {
		return teamRepository.getMember(memberId);
	}
	
	public void saveMember(Member member) {
		teamRepository.saveMember(member);
	}
	
	public void deleteMember(Member member) {
		teamRepository.deleteMember(member);
	}
	
	public JoinTeam joinTeam(String teamId, String memberId) {
		Team team = teamRepository.getTeam(teamId);
		Member member = teamRepository.getMember(memberId);
		
		JoinTeam joinTeam = new JoinTeam(team, member);
		
		teamRepository.saveJoinTeam(joinTeam);
		
		return joinTeam;
	}	
	
	public JoinTeam changeTeam(String memberId, String prevTeamId, String afterTeamId) {
		JoinTeam joinTeam = teamRepository.getJoinTeam(prevTeamId, memberId);
		
		Team team = teamRepository.getTeam(afterTeamId);
		
		joinTeam.changeTeam(team);
		
		teamRepository.saveJoinTeam(joinTeam);
		
		return joinTeam;
	}
	
	public void quitTeam(String memberId, String teamId) {
		JoinTeam joinTeam = teamRepository.getJoinTeam(teamId, memberId);
		
		teamRepository.deleteJoinTeam(joinTeam);
	}
	
}
