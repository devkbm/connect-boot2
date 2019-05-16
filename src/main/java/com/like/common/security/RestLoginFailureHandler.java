package com.like.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestLoginFailureHandler implements AuthenticationFailureHandler  {
			
	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		/*Enumeration<String> params = request.getParameterNames();
		
		while(params.hasMoreElements()){
		  String names = (String)params.nextElement();
		  log.info(names);
		 }*/
		
		log.info(exception.toString());
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");		
	}
}
