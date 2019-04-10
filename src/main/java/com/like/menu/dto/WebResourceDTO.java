package com.like.menu.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.like.menu.domain.model.QWebResource;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

public class WebResourceDTO {

	@Data
	public static class QueryCondition implements Serializable {
		
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
	
	@Data
	public static class ResourceSave implements Serializable {
				
		private static final long serialVersionUID = -1400051159309726788L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotEmpty
		String resourceCode;
			
		@NotEmpty
		String resourceName; 
		
		@NotEmpty
		String resourceType;
				
		@NotEmpty
		String url;
			
		String description;		
		
	}
}
