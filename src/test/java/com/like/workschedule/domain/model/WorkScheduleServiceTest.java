package com.like.workschedule.domain.model;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.user.domain.model.User;
import com.like.user.service.UserService;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.model.WorkGroupMember;
import com.like.workschedule.domain.model.id.WorkGroupMemberId;
import com.like.workschedule.service.WorkGroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WorkScheduleServiceTest {

	@Autowired
	WorkGroupService workGroupService;
	
	@Autowired
	UserService userService;
	
	@Before 
    public void setUp() throws Exception { 		
	}
	
	@Test
	@WithMockUser		
	public void test001_업무그룹생성() {
		//Given
		WorkGroup entity = this.createWorkGroup();
				
		//When
		workGroupService.saveWorkGroup(entity);
				
		//Then
		WorkGroup test = workGroupService.getWorkGroup(entity.getId());
		
		assertThat(test.getName()).isEqualTo("인사업무");
	}
	
	@Test
	@WithMockUser
	public void test002_업무그룹맴버추가() {
		//Given
		WorkGroup entity = this.createWorkGroup();
		
		User user = userService.getUser("1"); 
		User user2 = userService.getUser("2");
		//WorkGroupMember member = new WorkGroupMember(entity, user);
				
		workGroupService.saveWorkGroupMember(entity, user);				
		workGroupService.saveWorkGroupMember(entity, user2);
				
		//When
		//workGroupService.saveWorkGroupMember(entity, member);
		
		//Then
		WorkGroupMember test = workGroupService.getWorkGroupMember(new WorkGroupMemberId(entity.getId(), user.getUserId()));
		WorkGroupMember test2 = workGroupService.getWorkGroupMember(new WorkGroupMemberId(entity.getId(), user2.getUserId()));
		
				
		assertThat(test.id.getUserId()).isEqualTo("1");
		assertThat(test.id.getWorkGroupId()).isEqualTo(entity.getId());
		
		log.info(test.id.getUserId());
		log.info(test.id.getWorkGroupId().toString());
		log.info(test2.id.getUserId());
		log.info(test2.id.getWorkGroupId().toString());				
	}
	
	private WorkGroup createWorkGroup() {				
		return new WorkGroup("인사업무");
	}
	
	
	
}
