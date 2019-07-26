package com.like.user.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.user.domain.model.QAuthority;
import com.like.user.domain.model.QUser;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchCondition {
	
	@Data
	public static class AuthoritySearch implements Serializable {			
		
		private static final long serialVersionUID = -3030210553466518025L;

		private final QAuthority qAuthority = QAuthority.authority;
		
		String authority;
			
		String description;
			
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeAuthority(this.authority))
				   .and(likeDescription(this.description));					
			
			return builder;
		}
		
		private BooleanExpression likeAuthority(String authority) {
			if (StringUtils.isEmpty(authority)) {
				return null;
			}
			
			return qAuthority.authorityName.like("%"+authority+"%");
		}
		
		private BooleanExpression likeDescription(String description) {
			if (StringUtils.isEmpty(description)) {
				return null;
			}
			
			return qAuthority.description.like("%"+description+"%");
		}
		
	}
	
	
	@Data
	public static class UserSearch implements Serializable {

		private static final long serialVersionUID = -7886731992928427538L;

		private final QUser qUser = QUser.user;
		
		String userId;
		
		String name;
		
		String deptCode;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeUserId(this.userId))
			 	   .and(likeUserName(this.name))
			 	   .and(equalDeptCode(this.deptCode));						
			
			return builder;
		}
		
		private BooleanExpression likeUserId(String userId) {
			if (StringUtils.isEmpty(userId)) {
				return null;				
			}
						
			return qUser.userId.like("%"+userId+"%");
		}
		
		private BooleanExpression likeUserName(String name) {
			if (StringUtils.isEmpty(name)) {
				return null;
			}
						
			return qUser.name.like("%"+name+"%");
		}
		
		private BooleanExpression equalDeptCode(String deptCode) {
			if (StringUtils.isEmpty(deptCode)) {
				return null;
			}
			
			return qUser.dept.deptCode.eq(deptCode);
		}
	}

}
