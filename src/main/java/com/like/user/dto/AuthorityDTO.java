package com.like.user.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.QAuthority;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

public class AuthorityDTO {
	
	@Data
	public static class QueryCondition implements Serializable {			
		
		private static final long serialVersionUID = -3030210553466518025L;

		private final QAuthority qAuthority = QAuthority.authority;
		
		String authority;
			
		String description;
			
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			if (StringUtils.hasText(this.authority)) {
				builder.and(qAuthority.authorityName.like("%"+this.authority+"%"));
			}
			
			if (StringUtils.hasText(this.description)) {
				builder.and(qAuthority.description.like("%"+this.description+"%"));
			}
			
			return builder;
		}
		
	}
	
	@Data
	public static class AuthoritySave {

		LocalDateTime createdDt;	
			
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
				
		@NotBlank(message="권한은 필수 항목입니다.")
		String authority;
				
		String description;
		
		public Authority createAuthority() {
			return new Authority(this.authority, this.description);
		}
	}
}
