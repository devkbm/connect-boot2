package com.like.user.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.user.domain.model.Authority;
import com.like.user.domain.model.QAuthority;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

public class AuthorityDTO {
			
	@Data
	public static class AuthoritySave {

		LocalDateTime createdDt;	
			
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
				
		@NotEmpty(message="권한은 필수 항목입니다.")
		String authority;
				
		String description;
		
		public Authority createAuthority() {
			return new Authority(this.authority, this.description);
		}
	}
}
