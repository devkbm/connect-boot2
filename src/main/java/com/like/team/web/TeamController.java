package com.like.team.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.dept.dto.DeptDTO;
import com.like.team.domain.model.TeamMember;
import com.like.team.dto.TeamDTO;
import com.like.team.domain.model.Team;
import com.like.team.service.TeamService;
import com.like.user.domain.model.User;

@RestController
public class TeamController {

	@Autowired
	TeamService teamService;
	
	@GetMapping(value={"/grw/team/{id}"})
	public ResponseEntity<?> getTeam(@PathVariable(value="id") Long teamId) {
						
		Team team = teamService.getTeam(teamId);				
		
		return WebControllerUtil.getResponse(team,
				team == null ? 0 : 1, 
				team == null ? false : true,
				"조회 되었습니다.",
				HttpStatus.OK);													
	}
	
	@GetMapping(value={"/grw/team"})
	public ResponseEntity<?> getTeamList(@ModelAttribute TeamDTO.SearchCondition searchCondition) {
						
		List<Team> teamList = teamService.getTeamList(searchCondition);				
		
		return WebControllerUtil.getResponse(teamList,
				teamList.size(), 
				teamList.isEmpty()? false : true,
				teamList.size() + "건 조회 되었습니다.",
				HttpStatus.OK);												
	}
	
	@GetMapping(value={"/grw/team/{id}/member"})
	public ResponseEntity<?> getMemberList(@PathVariable(value="id") Long teamId) {
						
		List<User> memberList = teamService.getTeamMemberList(teamId);												
		
		return WebControllerUtil.getResponse(memberList,
				memberList == null ? 0 : 1, 
				memberList == null ? false : true,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
		
	@RequestMapping(value={"/grw/team"}, method={RequestMethod.POST,RequestMethod.PUT}) 
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
	
			
	@PostMapping(value={"/grw/member/{memberId}/join/{teamId}"})
	public ResponseEntity<?> joinTeam(
			@PathVariable(value="teamId") Long teamId,
			@PathVariable(value="memberId") String memberId) {				

		TeamMember joinTeam = teamService.joinTeam(teamId, memberId);			
										 					
		return WebControllerUtil.getResponse(joinTeam,
				1, 
				true, 
				String.format("팀에 등록 되었습니다."), 
				HttpStatus.OK);
	}
	
	
	@DeleteMapping(value={"/grw/member/{memberId}/team/{teamId}"})
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
