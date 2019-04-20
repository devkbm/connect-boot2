package com.like.team;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.team.domain.model.Team;
import com.like.team.domain.model.TeamMember;
import com.like.team.service.TeamService;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TeamServiceTest {

	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@Before 
    public void setUp() throws Exception { 		
	}
	
	@Test
	@WithMockUser
	@Ignore
	public void test001_팀생성및조회() {
		//Given
		Team team = this.createTeam();
				
		//When
		teamService.saveTeam(team, null);
				
		//Then
		Team test = teamService.getTeam(team.getTeamId());
		
		assertThat(test.getTeamName()).isEqualTo("개발팀");
	}
	
	//@Test
	public void test002_팀삭제() {
		//Given
		Team team = this.createTeam();
		teamService.saveTeam(team, null);
		
		//When
		teamService.deleteTeam(team);
		
		//Then
		log.info(team.toString());
		//assertThat(team).isNull();					
	}
	
	//@Test
	public void test003_팀가입() {
		//Given
		Team team = this.createTeam();
		teamService.saveTeam(team, null);
		
		User user = userService.getUser("1");
						
		//When
		teamService.joinTeam(team.getTeamId(), user.getUserId());
		
		//Then
		TeamMember test = teamService.getTeamMember(team.getTeamId(), user.getUserId());
		
		assertThat(test.getTeam()).isEqualTo(team);
		assertThat(test.getUser()).isEqualTo(user);
	}
	
	private Team createTeam() {				
		return new Team("개발팀");
	}
	
	
	
}
