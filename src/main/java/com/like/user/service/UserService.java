package com.like.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.menu.domain.model.MenuGroup;
import com.like.menu.service.MenuQueryService;
import com.like.user.boundary.AuthorityDTO;
import com.like.user.boundary.UserDTO;
import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.like.user.domain.service.UserDomainService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class UserService implements UserDetailsService {	
	
	@Autowired
	UserRepository userRepository;	
	
	@Resource
	private MenuQueryService menuQueryService;
	
	@Autowired
	UserDomainService userDomainService;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {					
		User user = userRepository.getUser(userId);
		
		if (user == null) {
			throw new UsernameNotFoundException(userId + " is Not Found");
		}
		
		return user;
	}
	
	/**
	 * 사용자 도메인을 조회한다.
	 * @param userId	사용자아이디
	 * @return 사용자 도메인
	 */
	public User getUser(String userId) {
		User user = userRepository.getUser(userId);
						
		return user;
	}
		
	public User getFullUser(String userId) {
		User user = userRepository.getFullUser(userId);
		List<MenuGroup> menuGroupList = user.getMenuGroupList();
		
		log.info(menuGroupList.toString());
		
		return user;				
	}
	
	public List<User> getUserList(List<String> userIds) {
		return userRepository.getUserList(userIds);
	}
	
	/**
	 * 유저 도메인 리스트를 조회한다.
	 * @return	유저 도메인 리스트
	 */
	public List<User> getUserList(UserDTO.SearchUser condition) {
		return userRepository.getUserList(condition);
	}
	
	/**
	 * 사용자를 생성한다.
	 * @param user	사용자 도메인
	 */
	public void createUser(User user) {
		//String rawPassword = user.getPassword();
		//String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
		//user.setPassword(encodedPassword);	
				
		userDomainService.createUser(user);						
	}
	
	public void saveUser(User user) {
		userRepository.saveUser(user);
	}
	
	/**
	 * 사용자 정보를 삭제한다.
	 * @param userId	사용자 아이디
	 */
	public void deleteUser(String userId) {
		 userRepository.deleteUser(userId);         
	}	
	
	/**
	 * 사용자 비밀번호를 변경한다.
	 * @param userId
	 * @param beforePassword	변경전 비밀번호
	 * @param afterPassword		변경후 비밀번호
	 */
	public void changePassword(String userId, String beforePassword, String afterPassword) {
		User user = userRepository.getUser(userId);			
		
		if ( user.isVaild(beforePassword) ) {
			user.changePassword(afterPassword);
		} 
	}
	
	/**
	 * 사용자의 비밀번호를 초기화한다.
	 * @param userId	사용자 아이디
	 */
	public void initPassword(String userId) {
		User user = userRepository.getUser(userId);
				
		user.initPassword();		
	}			
	
	/**
	 * 사용자 권한 리스트를 조회한다.
	 * @param userId	사용자아이디
	 * @return 사용자 권한 리스트
	 */
	public List<Authority> getAuthorities(String userId) {        									
        return userRepository.readAuthority(userId);
	}
	
	/**
	 * 전체 권한 도메인 리스트를 조회한다.
	 * @return	권한 도메인 리스트
	 */
	public List<Authority> getAuthorityList(AuthorityDTO.SearchAuthority condition) {        									
        return userRepository.getAuthorityList(condition);
	}
	
	/**
	 * 
	 * @param authorityNameList
	 * @return
	 */
	public List<Authority> getAllAuthorityList(List<String> authorityNameList) {        									
        return userRepository.getAuthorityList(authorityNameList);
	}
		
	/**
	 * 권한 도메인을 조회한다.
	 * @param authorityName	권한명
	 * @return	권한 도메인
	 */
	public Authority getAuthority(String authorityName) {
		return userRepository.getAuthority(authorityName);
	}
	
	/**
	 * 권한 도메인을 등록한다.
	 * @param authority	권한 도메인
	 */
	public void createAuthority(Authority authority) {
		userRepository.createAuthority(authority);
	}		
		
	/**
	 * 권한 도메인을 삭제한다.
	 * @param authorityName
	 */
	public void deleteAuthority(String authorityName) {
		userRepository.deleteAuthority(authorityName);
	}
	/**
	 * 중복 유저 검증 기능
	 * @param userId
	 * @return 기존 아이디가 있으면 true, 아니면 false 리턴
	 */
	public boolean CheckDuplicationUser(String userId) {						
		return userRepository.getUser(userId) != null ? true : false; 
	}	
	
	public PasswordEncoder passwordEncoder(){
		return this.passwordEncoder;
	}	
}
