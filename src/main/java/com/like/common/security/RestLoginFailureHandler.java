package com.like.common.security;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class RestLoginFailureHandler implements AuthenticationFailureHandler  {
	
	private static final Logger log = LoggerFactory.getLogger(RestLoginFailureHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		/*Enumeration<String> params = request.getParameterNames();
		
		while(params.hasMoreElements()){
		  String names = (String)params.nextElement();
		  log.info(names);
		 }*/
		
		
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");		
	}
}
