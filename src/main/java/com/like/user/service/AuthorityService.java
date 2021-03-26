package com.like.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.user.boundary.AuthorityDTO;
import com.like.user.domain.model.Authority;
import com.like.user.domain.repository.AuthorityRepository;

@Transactional
@Service
public class AuthorityService {

	private AuthorityRepository repository;
	
	public AuthorityService(AuthorityRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * 권한 도메인을 조회한다.
	 * @param authorityName	권한명
	 * @return	권한 도메인
	 */
	public Authority getAuthority(String authorityName) {
		return repository.findById(authorityName).orElse(null);
	}
	
	/**
	 * 권한 도메인을 등록한다.
	 * @param authority	권한 도메인
	 */
	public void createAuthority(AuthorityDTO.SaveAuthority dto) {
		Authority authority = null;
		
		if (dto.getAuthority() != null) {
			authority = repository.findById(dto.getAuthority()).orElse(null);
		} 
		
		if (authority == null) {
			authority = dto.newAuthority();
		} else {
			dto.modifyAuthority(authority);
		}
		
		repository.save(authority);
	}		
		
	/**
	 * 권한 도메인을 삭제한다.
	 * @param authorityName
	 */
	public void deleteAuthority(String authorityName) {
		repository.deleteById(authorityName);
	}
}
