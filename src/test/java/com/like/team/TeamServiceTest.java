package com.like.team;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.team.domain.model.Member;
import com.like.team.domain.model.Team;
import com.like.team.service.TeamService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TeamServiceTest {

	@Autowired
	TeamService teamService;
	
	@Before 
    public void setUp() throws Exception { 
		Team team = new Team("team00","팀테스트");
		
		teamService.saveTeam(team);
		
		Member member = new Member("member00","멤버00");
		
		teamService.saveMember(member);
	}
	
	@Test
	public void 팀조회() {
		Team team = teamService.getTeam("team00");
		
		assertThat(team.getTeamId(),	is("team00"));
		assertThat(team.getTeamName(),	is("팀테스트"));
	}
	
	@Test
	public void 팀저장() {
		Team team = new Team("team01","팀테스트");
		
		teamService.saveTeam(team);			
	}
	
	@Test
	public void 멤버저장() {
		Member member = new Member("member001","멤버001");
		
		teamService.saveMember(member);		
	}
	
	@Test
	public void 팀가입() {
				
		teamService.joinTeam("team00", "member00");
	
	}
}
