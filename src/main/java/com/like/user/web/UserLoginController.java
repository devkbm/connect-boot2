package com.like.user.web;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.dept.domain.repository.DeptRepository;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.repository.MenuRepository;
import com.like.menu.service.MenuQueryService;
import com.like.user.domain.model.AuthenticationToken;
import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.like.user.dto.LoginRequestDTO;
import com.like.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserLoginController {

	@Autowired
	UserRepository userRepository;
	
	@Resource(name="menuJpaRepository")
	private MenuRepository menuRepository;
	
	@Resource(name="deptJpaRepository")
	private DeptRepository deptRepository;	
	
	@Autowired 
	AuthenticationManager authenticationManager;
	
	@Resource
	UserService userService;
	
	@Resource
	MenuQueryService menuQueryService;
	
	
	private void authentication(String username, String password, List<GrantedAuthority> authorities, HttpSession session) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, authorities);
		
		Authentication authentication = authenticationManager.authenticate(token); 
							
		SecurityContextHolder.getContext().setAuthentication(authentication); 						
		
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		
		// log.info(SecurityContextHolder.getContext().getAuthentication().getName() + " 로그인 되었습니다.");
		// log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
	}
		 
	@PostMapping(value={"/common/user/login"})
	public AuthenticationToken login(@RequestBody @Valid LoginRequestDTO dto, HttpSession session, BindingResult result) {
		
		if ( result.hasErrors() ) {
			log.info(result.toString());
			return null;
		}			
		
		String username = dto.getUsername();
		String password = dto.getPassword();

		User user = userService.getUser(username);
		
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)user.getAuthorities();           						
        authentication(username, password, authorities, session);         		 							       
                
		return AuthenticationToken
				.builder()
				.userId(user.getUsername())
				.userName(user.getName())
				.imageUrl(user.getImage())
				.collection(user.getAuthorities())
				.menuGroupList(user.getMenuGroupList())
				.token(session.getId())
				.build();
	}	
}
