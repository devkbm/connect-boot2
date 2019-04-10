package com.like.common.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.like.common.security.RestLoginFailureHandler;
import com.like.common.security.RestLoginSuccessHandler;
import com.like.common.security.RestLogoutSuccessHandler;
import com.like.common.security.RequestBodyReaderAuthenticationFilter;
import com.like.common.security.RestAuthenticationEntryPoint;
import com.like.user.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	/*
	 * 인증되지 않은 접근에 대해 redirect(302)시키지 않고 401 Status 리턴
	 */
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
		
	/**
	 * 로그인 성공후 default target page로 redirect(302)하지 않고 Http Status(200) 리턴
	 */
	@Autowired
	private RestLoginSuccessHandler authSuccessHandler;
	
	/**
	 * 로그인 실패 시 redirect(302)하지 않고 Http Status(401) 리턴
	 */
	@Autowired
	private RestLoginFailureHandler authFailureHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable()
		//    .headers().frameOptions().disable();			
		
		http.csrf().disable()
			.cors().and()			
			//.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER).and()			
			.authorizeRequests()
			.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()							
				.antMatchers("/user/login").permitAll()
				//.antMatchers("/grw/**").permitAll()//hasRole("USER")							
				//.anyRequest().authenticated().and()		// 인증된 요청만 허용
				.anyRequest().permitAll().and()				// 모든 요청 허용(테스트용도)
			// 모든 연결을 HTTPS로 강제 전환
			//.requiresChannel().anyRequest().requiresSecure().and()
			/*.formLogin()
				.loginProcessingUrl("/auth/login")
				.usernameParameter("username")
				.passwordParameter("password")*/
				//.successHandler(authSuccessHandler)
				//.failureHandler(authFailureHandler)
				//.successHandler(this::loginSuccessHandler)
				//.failureHandler(this::loginFailureHandler)
				//.permitAll().and()
			.logout()
				.logoutUrl("/auth/logout")
				//.logoutSuccessHandler(logoutSuccessHandler)
				.logoutSuccessHandler(this::logoutSuccessHandler)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll();		
			//http.portMapper().http(8080).mapsTo(8443);
		//http.addFilterBefore(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);	
		
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
       CorsConfiguration configuration = new CorsConfiguration();              
       configuration.setAllowedOrigins(Arrays.asList("*"));
       configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
       configuration.setAllowedHeaders(Arrays.asList("origin","Content-Type", "Accept", "X-Requested-With", "remember-me", "x-auth-token"));       
       configuration.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin","Access-Control-Allow-Credentials"));
       configuration.setAllowCredentials(true);
       configuration.setMaxAge(3600L);
       
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", configuration);
       
       return source;
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
	}
	
	/*@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}*/
		
	@Bean
	public PasswordEncoder noOpPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(this.noOpPasswordEncoder());		
	}
	
	@Bean
	public RequestBodyReaderAuthenticationFilter myAuthenticationFilter() throws Exception {
		RequestBodyReaderAuthenticationFilter authenticationFilter = new RequestBodyReaderAuthenticationFilter();
		authenticationFilter.setAuthenticationManager(this.authenticationManagerBean());		
		// 여기서 직접 만든 authenticationManagerBean을 설정 해도 됨
		authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));
		// loginProcessingUrl에 설정한 주소로 했을 때 오류가 생겨서 그냥 default 값으로 지정함
		return authenticationFilter;
	}
	
	
	private void loginSuccessHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) { 
		
        response.setStatus(HttpStatus.OK.value());
    }
 
    private void loginFailureHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException e) throws IOException {
         
        response.sendError(HttpStatus.UNAUTHORIZED.value());        
    }
 
    private void logoutSuccessHandler(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException {
 
        response.setStatus(HttpStatus.OK.value());
    }   
    
}
