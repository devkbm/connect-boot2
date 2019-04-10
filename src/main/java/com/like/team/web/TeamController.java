package com.like.team.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.team.domain.model.JoinTeam;
import com.like.team.domain.model.Member;
import com.like.team.domain.model.Team;
import com.like.team.service.TeamService;

@RestController
public class TeamController {

	@Autowired
	TeamService teamService;
	
	@GetMapping(value={"/team/{id}"})
	public ResponseEntity<?> getTeam(@PathVariable(value="id") String teamId) {
						
		Team team = teamService.getTeam(teamId);												
		
		return WebControllerUtil.getResponse(team,
				team == null ? 0 : 1, 
				team == null ? false : true,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
	
	@GetMapping(value={"/team/{id}/members"})
	public ResponseEntity<?> getMemberList(@PathVariable(value="id") String teamId) {
						
		List<Member> memberList = teamService.getMemberList(teamId);												
		
		return WebControllerUtil.getResponse(memberList,
				memberList == null ? 0 : 1, 
				memberList == null ? false : true,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
	
	@GetMapping(value={"/member/{id}/teams"})
	public ResponseEntity<?> getTeamList(@PathVariable(value="id") String memberId) {
						
		List<Team> teamList = teamService.getTeamList(memberId);												
		
		return WebControllerUtil.getResponse(teamList,
				teamList == null ? 0 : 1, 
				teamList == null ? false : true,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/team"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveTeam(@Valid @RequestBody Team team, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
																			
		teamService.saveTeam(team);			
										 					
		return WebControllerUtil.getResponse(team,
				team != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", team != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@GetMapping(value={"/member/{id}"})
	public ResponseEntity<?> getMebmer(@PathVariable(value="id") String memberId) {
						
		Member member = teamService.getMember(memberId);		
		
		return WebControllerUtil.getResponse(member,
				member == null ? 0 : 1, 
				member == null ? false : true,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/member"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMember(@Valid @RequestBody Member member, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
																			
		teamService.saveMember(member);			
										 					
		return WebControllerUtil.getResponse(member,
				member != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", member != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@PostMapping(value={"/member/{memberId}/join/{teamId}"})
	public ResponseEntity<?> joinTeam(
			@PathVariable(value="teamId") String teamId,
			@PathVariable(value="memberId") String memberId) {				

		JoinTeam joinTeam = teamService.joinTeam(teamId, memberId);			
										 					
		return WebControllerUtil.getResponse(joinTeam,
				1, 
				true, 
				String.format("팀에 등록 되었습니다."), 
				HttpStatus.OK);
	}
	
	@PostMapping(value={"/member/{memberId}/team/{teamId}/change/{afterTeamId}"})
	public ResponseEntity<?> changeTeam(
			@PathVariable(value="teamId") String teamId,
			@PathVariable(value="memberId") String memberId,
			@PathVariable(value="afterTeamId") String afterTeamId) {				

		JoinTeam joinTeam = teamService.changeTeam(memberId, teamId, afterTeamId);			
										 					
		return WebControllerUtil.getResponse(joinTeam,
				1, 
				true, 
				String.format("팀이 변경되었습니다."), 
				HttpStatus.OK);
	}
	
	@DeleteMapping(value={"/member/{memberId}/team/{teamId}"})
	public ResponseEntity<?> deleteTeam(
			@PathVariable(value="teamId") String teamId,
			@PathVariable(value="memberId") String memberId) {				

		teamService.quitTeam(memberId, teamId);	
										 					
		return WebControllerUtil.getResponse(memberId,
				1, 
				true, 
				String.format("팀에서 제외되었습니다."), 
				HttpStatus.OK);
	}
	
}
