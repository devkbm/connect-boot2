package com.like.user.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.like.user.boundary.LoginRequestDTO;
import com.like.user.domain.model.AuthenticationToken;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserLoginController {
		
	private AuthenticationManager authenticationManager;
		
	private UserService userService;		 	   
				
	public UserLoginController(AuthenticationManager authenticationManager, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
	}

	private void authentication(String username, String password, List<GrantedAuthority> authorities, HttpSession session) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, authorities);
		
		Authentication authentication = authenticationManager.authenticate(token); 
							
		SecurityContextHolder.getContext().setAuthentication(authentication); 						
		
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		
		// log.info(SecurityContextHolder.getContext().getAuthentication().getName() + " 로그인 되었습니다.");
		// log.info(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
	}
		 
	@PostMapping(value={"/common/user/login"})
	public AuthenticationToken login(@RequestBody @Valid LoginRequestDTO dto, HttpSession session, BindingResult result, HttpServletRequest request) {
		
		if ( result.hasErrors() ) {			
			return null;
		}			
		
		String username = dto.getUsername();
		String password = dto.getPassword();
		log.info(username);
		User user = userService.getUser(username);
		
		List<GrantedAuthority> authorities = (List<GrantedAuthority>)user.getAuthorities();           						
        authentication(username, password, authorities, session);         		 							       
        
        //userService.saveLogInOutHistory(new LogInOutHistory(dto.getUsername(), "LOGIN", this.getClientIp(request), true));
        
		return AuthenticationToken
				.builder()
				.userId(user.getUsername())
				.userName(user.getName())
				.imageUrl(user.getImage())
				.email("")
				.token(session.getId())
				.collection(user.getAuthorities().stream().map(o -> o.getAuthority()).collect(Collectors.toList()))
				.menuGroupList(user.getMenuGroupList())				
				.build();
	}	
	
    private String getClientIp(HttpServletRequest request) {
    	 
        String ip = request.getHeader("X-Forwarded-For");
          
        if (ip == null) ip = request.getHeader("Proxy-Client-IP");		// Proxy           
        if (ip == null) ip = request.getHeader("WL-Proxy-Client-IP"); 	// 웹로직
        if (ip == null) ip = request.getHeader("HTTP_CLIENT_IP");        
        if (ip == null) ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (ip == null) ip = request.getRemoteAddr();
               
        //logger.info(">>>> Result : IP Address : "+ip);
 
        return ip; 
    }          
    
}
