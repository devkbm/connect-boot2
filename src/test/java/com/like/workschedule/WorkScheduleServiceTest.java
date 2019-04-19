package com.like.workschedule;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.service.WorkGroupService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WorkScheduleServiceTest {

	@Autowired
	WorkGroupService workGroupService;
	
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
	
	private WorkGroup createWorkGroup() {				
		return new WorkGroup("인사업무");
	}
	
	
	
}
