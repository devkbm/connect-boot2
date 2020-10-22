package com.like.user.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.user.boundary.AuthorityDTO;
import com.like.user.domain.model.Authority;
import com.like.user.service.UserService;

@RestController
public class UserAuthorityController {

	private UserService userService;
	
	public UserAuthorityController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value={"/common/authority"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getAuthorityList(AuthorityDTO.SearchAuthority dto) {				
		
		List<Authority> authorityList = userService.getAuthorityList(dto);								 				
		
		return WebControllerUtil
				.getResponse(authorityList
							,authorityList.size()
							,authorityList.size() > 0 ? true : false
							,"조회 되었습니다."
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/common/authority/{id}"}, method=RequestMethod.GET) 
	public ResponseEntity<?> getAuthority(@PathVariable(value="id") String authorityName) {			
		
		Authority authority = userService.getAuthority(authorityName);										
		
		return WebControllerUtil
				.getResponse(authority
							,authority == null ? 0 : 1
							,authority == null ? false : true
							,"조회 되었습니다."
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/common/authority"}, method={RequestMethod.POST,RequestMethod.PUT})	
	public ResponseEntity<?> saveAuthority(@RequestBody AuthorityDTO.SaveAuthority dto, BindingResult result) {
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		}		
		
		userService.createAuthority(dto);					
																				 				
		return WebControllerUtil
				.getResponse(null
							,1
							,true
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}	
	
	@DeleteMapping("/common/authority/{id}")
	public ResponseEntity<?> deleteAuthority(@PathVariable(value="id") String authorityName) {
		
		userService.deleteAuthority(authorityName);					
			
		return WebControllerUtil
				.getResponse(null
							,1
							,true
							,String.format("%d 건 삭제되었습니다.", 1)
							,HttpStatus.OK);
	}
}
