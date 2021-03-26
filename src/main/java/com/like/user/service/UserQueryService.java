package com.like.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.user.boundary.UserDTO;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserQueryRepository;


@Service
@Transactional(readOnly = true)
public class UserQueryService  {

	private UserQueryRepository repository;
	
	public UserQueryService(UserQueryRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * 유저 도메인 리스트를 조회한다.
	 * @return	유저 도메인 리스트
	 */
	public List<User> getUserList(UserDTO.SearchUser condition) {
		return repository.getUserList(condition);
	}		
	
}
