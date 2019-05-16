package com.like.common.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.like.user.domain.model.AuthenticationToken;
import com.like.user.domain.model.User;
import com.like.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RestLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Resource
	private UserService loginService;
	
	@Autowired HttpSession session;
	
	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	
	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
    	
		log.info(authentication.getName());        
		log.info(SecurityContextHolder.getContext().getAuthentication().getName());		
						
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		
		
		User user = loginService.getUser(authentication.getName());
		
		AuthenticationToken token = AuthenticationToken
										.builder()
										.userId(user.getUsername())
										.userName(user.getName())
										.imageUrl(user.getImage())
										.collection(user.getAuthorities())
										.menuGroupList(user.getMenuGroupList())
										.token(this.session.getId())
										.build();
		
		pw.write(token.toString());
		pw.flush();
		
		log.info(this.session.getId());
		
        SavedRequest savedRequest = requestCache.getRequest(request, response);        
        log.info(savedRequest == null ? "true" : "false");
                
        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
                
        log.info("222");
        String targetUrlParam = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParam != null && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }
        log.info("333");
        
        clearAuthenticationAttributes(request);
                
	}
			
}
