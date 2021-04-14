package com.like.core.security;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.like.user.boundary.LoginRequestDTO;

public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private static final Logger log = LoggerFactory.getLogger(RequestBodyReaderAuthenticationFilter.class);
	
	private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";
	 
    private final ObjectMapper objectMapper = new ObjectMapper(); 
	
	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		
		String requestBody;		
		UsernamePasswordAuthenticationToken token;
		LoginRequestDTO authRequest;
		
		try {
			requestBody = convertString(request.getReader());
			
			if ( requestBody != null ) {			
				authRequest = objectMapper.readValue(requestBody, LoginRequestDTO.class);
				token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
				setDetails(request, token);
			} else {
				throw new InternalAuthenticationServiceException("HttpRequest is Null");					
			}
			log.info(requestBody);
			log.info(token.toString());
				                                                           
		} catch (Exception e) {
			log.error("error : " + ERROR_MESSAGE);
			throw new InternalAuthenticationServiceException(ERROR_MESSAGE, e);			
		}		
		
		return this.getAuthenticationManager().authenticate(token);			
	}
	
	private String convertString(BufferedReader reader) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
	    String line;
	    
	    while ((line = reader.readLine()) != null) {
	        stringBuilder.append(line);
	    }	    
	    return stringBuilder.toString();	
	}
	
}
