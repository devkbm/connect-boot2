package com.like.user.domain.repository;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.User;
import com.like.user.dto.AuthorityDTO;
import com.like.user.dto.UserDTO;

public interface UserRepository {
			
	/**
	 * 유저 도메인을 조회한다.
	 * @param userId	사용자 아이디
	 * @return	유저 도메인
	 */
	User getUser(String userId) throws UsernameNotFoundException;
		
	User getFullUser(String userId);
	
	List<User> getUserList(List<String> userIds);
	
	/**
	 * 유저 도메인 리스트를 조회한다.
	 * @return	유저 도메인 리스트
	 */
	List<User> getUserList(UserDTO.QueryCondition condition);

	/**
	 * 유저 도메인을  저장한다.
	 * @param user	유저 도메인
	 */
	void saveUser(User user);

	/**
	 * 유저를 삭제한다.
	 * @param userId	사용자 아이디
	 */
	void deleteUser(String userId);
	
	/**
	 * 사용자에 권한을 추가한다.
	 * @param userId	사용자 아이디
	 * @param authority	권한
	 */
	void addUserAuthority(String userId, Authority authority);
	
	/**
	 * 유저 권한을 조회한다.
	 * @param userId	사용자 아이디
	 * @return	권한 도메인 리스트
	 */
	List<Authority> readAuthority(String userId);	
	
	/**
	 * 전체 권한 도메인 리스트를 조회한다.
	 * @return	권한 도메인 리스트
	 */
	List<Authority> getAuthorityList(AuthorityDTO.QueryCondition condition);
	
	/**
	 * 
	 * @param authorityNameList 권한 명칭 리스트
	 * @return	권한 도메인 리스트
	 */
	List<Authority> getAuthorityList(List<String> authorityNameList);
	
	/**
	 * 권한 도메인을 조회한다.
	 * @param authorityName	권한명칭
	 * @return 권한 도메인
	 */
	Authority getAuthority(String authorityName);
	
	/**
	 * 신규 권한을 등록한다.
	 * @param authority	권한 도메인
	 */
	void createAuthority(Authority authority);
		
	/**
	 * 유저의 권한을 삭제한다. 
	 * @param userId	사용자 아이디
	 */
	void deleteAuthority(String userId);
}
