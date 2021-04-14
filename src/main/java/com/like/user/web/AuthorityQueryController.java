package com.like.user.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.user.boundary.AuthorityDTO;
import com.like.user.domain.model.Authority;
import com.like.user.service.AuthorityQueryService;

@RestController
public class AuthorityQueryController {

	private AuthorityQueryService service;
	
	public AuthorityQueryController(AuthorityQueryService service) {
		this.service = service;		
	}
	
	@RequestMapping(value={"/common/authority"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getAuthorityList(AuthorityDTO.SearchAuthority dto) {				
		
		List<Authority> authorityList = service.getAuthorityList(dto);								 				
		
		return WebControllerUtil
				.getResponse(authorityList							
							,String.format("%d 건 조회되었습니다.", authorityList.size())
							,HttpStatus.OK);
	}
}
