package com.like.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;



import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.like.user.domain.model.User;
import com.like.user.dto.UserDTO;
import com.like.user.dto.UserDTO.UserSave;
import com.like.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {
		 		
	@Autowired
	UserService userService;
						
	@Test	
	public void test01_사용자등록및조회() {
		//Given		
		User user = User.builder()
						.userId("test01")
						.name("테스트")
						.password("12345678")
						.authorities(null)
						.menuGroupList(null)											
						.build();
		
				
		//When
		userService.createUser(user);
		
		//Then
		User test = userService.getUser(user.getUserId());
		
		assertThat(test.getUsername()).isEqualTo("test01");
		assertThat(test.getName()).isEqualTo("테스트");
		assertThat(test.getPassword()).isEqualTo("12345678");			
		assertThat(test.isEnabled()).isEqualTo(true);
		assertThat(test.isAccountNonExpired()).isEqualTo(true);
		assertThat(test.isAccountNonLocked()).isEqualTo(true);
		assertThat(test.isCredentialsNonExpired()).isEqualTo(true);		
	}
		
	
	
	
}
