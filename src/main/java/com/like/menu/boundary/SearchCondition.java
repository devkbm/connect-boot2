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
			
			if (StringUtils.hasText(this.menuGroupCode)) {
				builder.and(qMenuGroup.menuGroupCode.like("%"+this.menuGroupCode+"%"));
			}
			
			if (StringUtils.hasText(this.menuGroupName)) {
				builder.and(qMenuGroup.menuGroupName.like("%"+this.menuGroupName+"%"));
			}			
			
			return builder;
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
			
			if (StringUtils.hasText(this.resourceCode)) {
				builder.and(qWebResource.resourceCode.like("%"+this.resourceCode+"%"));
			}
			
			if (StringUtils.hasText(this.resourceName)) {
				builder.and(qWebResource.resourceName.like("%"+this.resourceName+"%"));
			}
			
			if (StringUtils.hasText(this.resourceType)) {
				builder.and(qWebResource.resourceType.like("%"+this.resourceType+"%"));
			}
			
			if (StringUtils.hasText(this.url)) {
				builder.and(qWebResource.url.like("%"+this.url+"%"));
			}
			
			if (StringUtils.hasText(this.description)) {
				builder.and(qWebResource.description.like("%"+this.description+"%"));
			}
			
			return builder;
		}
	}
}
