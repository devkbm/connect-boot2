package com.like.menu.boundary;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.menu.domain.model.QMenu;
import com.like.menu.domain.model.QMenuGroup;
import com.like.menu.domain.model.QWebResource;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchCondition {

	@Data
	public static class MenuSearch implements Serializable {
		
		private static final long serialVersionUID = -7394537330230941998L;

		private final QMenu qMenu = QMenu.menu;
		
		@NotEmpty
		String menuGroupCode;
		
		String menuCode;
		
		String menuName;
				
		public BooleanBuilder getBooleanBuilder() {																
			return new BooleanBuilder()
					.and(equalMenuGroupCode(this.menuGroupCode))
					.and(likeMenuCode(this.menuCode))
					.and(likeMenuName(this.menuName));
		}
		
		private BooleanExpression equalMenuGroupCode(String menuGroupCode) {					
			return QMenuGroup.menuGroup.menuGroupCode.eq(menuGroupCode);
		}
		
		private BooleanExpression likeMenuCode(String menuCode) {
			if (StringUtils.isEmpty(menuCode)) {
				return null;
			}
			
			return qMenu.menuCode.like("%"+menuCode+"%");
		}
		
		private BooleanExpression likeMenuName(String menuName) {
			if (StringUtils.isEmpty(menuName)) {
				return null;
			}
			
			return qMenu.menuName.like("%"+menuName+"%");
		}
	}
	
	@Data
	public static class MenuGroupSearch implements Serializable {

		private static final long serialVersionUID = 4855967336075857695L;

		private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;
		
		String menuGroupCode;
		
		String menuGroupName;
				
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeMenGroupCode(this.menuGroupCode))
				.and(likeMenGroupName(this.menuGroupName));
											
			return builder;
		}
		
		private BooleanExpression likeMenGroupCode(String menuGroupCode) {
			if (StringUtils.isEmpty(menuGroupCode)) {
				return null;
			}
			
			return qMenuGroup.menuGroupCode.like("%"+menuGroupCode+"%");
		}
		
		private BooleanExpression likeMenGroupName(String menuGroupName) {
			if (StringUtils.isEmpty(menuGroupName)) {
				return null;
			}
			
			return qMenuGroup.menuGroupName.like("%"+menuGroupName+"%");
		}
		
		
	}
	
	
	@Data
	public static class WebResourceSearch implements Serializable {
		
		private static final long serialVersionUID = 698694617356322910L;

		private final QWebResource qWebResource = QWebResource.webResource;
		
		String resourceCode;
		
		String resourceName;
		
		String resourceType;
		
		String url;
		
		String description;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeResourceCode(this.resourceCode))
				.and(likeResourceName(this.resourceName))
				.and(likeResourceType(this.resourceType))
				.and(likeUrl(this.url))
				.and(likeDescription(this.description));									
											
			return builder;
		}
		
		private BooleanExpression likeResourceCode(String resourceCode) {
			if (StringUtils.isEmpty(resourceCode)) {
				return null;
			}
			
			return qWebResource.resourceCode.like("%"+resourceCode+"%");
		}
		
		private BooleanExpression likeResourceName(String resourceName) {
			if (StringUtils.isEmpty(resourceName)) {
				return null;
			}
			
			return qWebResource.resourceName.like("%"+resourceName+"%");
		}
		
		private BooleanExpression likeResourceType(String resourceType) {
			if (StringUtils.isEmpty(resourceType)) {
				return null;
			}
			
			return qWebResource.resourceType.like("%"+resourceType+"%");
		}
		
		private BooleanExpression likeUrl(String url) {
			if (StringUtils.isEmpty(url)) {
				return null;
			}
			
			return qWebResource.url.like("%"+url+"%");
		}
		
		private BooleanExpression likeDescription(String description) {
			if (StringUtils.isEmpty(description)) {
				return null;
			}
			
			return qWebResource.description.like("%"+description+"%");
		}
	}
}
