package com.like.common.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.like.user.service.UserService;

@Component
public class RestLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private static final Logger log = LoggerFactory.getLogger(RestLoginSuccessHandler.class);
	
	/*@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {

		response.setStatus(HttpServletResponse.SC_OK);			
	}*/
	
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Resource
	private UserService loginService;
	
	public void setRequestCache(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
    	
		Enumeration<String> params = request.getParameterNames();
		
		log.info("RestLoginSuccessHandler");
		
		while(params.hasMoreElements()){
		  String names = (String)params.nextElement();
		  log.info(names);
		 }
		
		
		handle(request, response, authentication);
    	
    	// Get Current User Info
		/*String userId = authentication.getName();
		UserVo currentUser = loginService.getUser(userId);

		// Save Current User Info into Session
		HttpSession session = request.getSession();
		session.setAttribute("currentUser", currentUser);
		clearAuthenticationAttributes(request);

		// response Current Login User Info via JSON String
		ObjectMapper mapper = new ObjectMapper();
		String jsonStr = mapper.writeValueAsString(currentUser);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jsonStr);*/
		
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        final SavedRequest savedRequest = requestCache.getRequest(request, response);

        if (savedRequest == null) {
            clearAuthenticationAttributes(request);
            return;
        }
        String targetUrlParam = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParam != null && StringUtils.hasText(request.getParameter(targetUrlParam)))) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
            return;
        }
        clearAuthenticationAttributes(request);
	}			
}
