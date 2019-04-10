package com.like.user.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;

@Service
public class UserDomainService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void createUser(User user) {
		
		if (user.getUsername() == null) {
			new IllegalArgumentException("유저 아이디가 존재하지 않습니다.");
		}
		
		if ( user.getAuthorityList().isEmpty() ) {
			initAuthority(user);
		}
		
		userRepository.saveUser(user);				
	}
	
	/**
	 * 사용자 신규등록시 권한이 없을 경우 기본 권한을 추가한다.
	 * @param user	사용자 도메인
	 */
	private void initAuthority(User user) {							
		List<Authority> authorities = new ArrayList<Authority>();
		
		authorities.add(userRepository.getAuthority("ROLE_USER"));			
		user.setAuthorities(authorities);
	}
		
}
