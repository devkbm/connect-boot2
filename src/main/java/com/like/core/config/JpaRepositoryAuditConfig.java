package com.like.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.like.core.audit.AuditorAwareImpl;

@Configuration
@EnableJpaAuditing
public class JpaRepositoryAuditConfig {

	@Bean
	public AuditorAware<String> auditorProvider() {
	    return new AuditorAwareImpl(); // AuditorAware 의 구현체 객체 생성	    
	}
}
