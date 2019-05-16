package com.like.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {	
	
	@Override
	public void commence(
			final HttpServletRequest request, 
			final HttpServletResponse response,
			final AuthenticationException authException) throws IOException, ServletException {
								
		// 인증 없이 Request 수신시 "Unauthorized(401)" 응답
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());		
	}

}
