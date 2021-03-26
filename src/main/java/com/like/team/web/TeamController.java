package com.like.team.web;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import com.like.team.domain.model.TeamMember;
import com.like.team.boundary.TeamDTO;
import com.like.team.domain.model.Team;
import com.like.team.domain.model.TeamDTOAssembler;
import com.like.team.service.TeamService;
import com.like.user.boundary.UserDTO;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;


@RestController
public class TeamController {
		
	private TeamService teamService;		
	private UserRepository userRepository;
	
	public TeamController(TeamService teamService
						 ,UserRepository userRepository) {
		this.teamService = teamService;
		this.userRepository = userRepository;
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
	
	@GetMapping(value={"/grw/team/{id}"})
	public ResponseEntity<?> getTeam(@PathVariable(value="id") Long teamId) {
						
		Team team = teamService.getTeam(teamId);				
		
		TeamDTO.TeamSave dto = TeamDTOAssembler.convertDTO(team);
		
		return WebControllerUtil.getResponse(dto,
				team == null ? 0 : 1, 
				team == null ? false : true,
				"조회 되었습니다.",
				HttpStatus.OK);													
	}
	
	@RequestMapping(value={"/grw/team"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveTeam(@Valid @RequestBody TeamDTO.TeamSave dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
		
		Team team = dto.getTeamId() == null ? null : teamService.getTeam(dto.getTeamId());		
		
		if (team == null) {
			team = new Team(dto.getTeamName());						
		} else {
			team.changeTeamName(dto.getTeamName());
		}
						
		List<User> userList = null;		
		if (dto.getMemberList() != null) {
			userList = userRepository.findAllById(dto.getMemberList());					 											
		}
		
		teamService.saveTeam(team, userList);		
										 					
		return WebControllerUtil.getResponse(team,
				team != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", team != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@GetMapping(value={"/grw/allmember"})
	public ResponseEntity<?> getAllMemberList(UserDTO.SearchUser condition) {
				
		List<User> userList = teamService.getAllMember(condition);						 				
		
		return WebControllerUtil.getResponse(userList,
				userList.size(), 
				userList.size() > 0 ? true : false ,
				"조회 되었습니다.",
				HttpStatus.OK);
	}
	
	@GetMapping(value={"/grw/team/{id}/member"})
	public ResponseEntity<?> getTeamMemberList(@PathVariable(value="id") Long teamId) {
						
		List<User> memberList = teamService.getTeamMemberList(teamId);												
		
		return WebControllerUtil.getResponse(memberList,
				memberList == null ? 0 : 1, 
				memberList == null ? false : true,
				"조회 되었습니다.",
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
		
	
}
