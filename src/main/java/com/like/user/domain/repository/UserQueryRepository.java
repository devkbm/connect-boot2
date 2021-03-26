package com.like.user.domain.repository;

import java.util.List;

import com.like.user.boundary.UserDTO;
import com.like.user.domain.model.User;

public interface UserQueryRepository {

	/**
	 * 유저 도메인 리스트를 조회한다.
	 * @return	유저 도메인 리스트
	 */
	List<User> getUserList(UserDTO.SearchUser condition);
	
}
