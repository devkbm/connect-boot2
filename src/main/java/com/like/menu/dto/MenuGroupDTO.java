package com.like.menu.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.menu.domain.model.QMenuGroup;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

public class MenuGroupDTO {

	@Data
	public static class QueryCondition implements Serializable {

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
	public static class MenuGroupSave implements Serializable {
		
		private static final long serialVersionUID = 3108072896516171536L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotEmpty	
		private String menuGroupCode;
		
		@NotEmpty	
		private String menuGroupName;
				
		private String description;		
			
	}
}
