package com.like.user.domain.repository;

import java.util.List;

import com.like.user.boundary.AuthorityDTO;
import com.like.user.domain.model.Authority;

public interface AuthorityQueryRepository {

	/**
	 * 전체 권한 도메인 리스트를 조회한다.
	 * @return	권한 도메인 리스트
	 */
	List<Authority> getAuthorityList(AuthorityDTO.SearchAuthority condition);
	
}
