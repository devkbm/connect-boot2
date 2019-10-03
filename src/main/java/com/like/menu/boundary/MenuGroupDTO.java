package com.like.menu.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.QMenuGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MenuGroupDTO {

	@Data
	public static class SearchMenuGroup implements Serializable {

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
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SaveMenuGroup implements Serializable {
		
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
		
		public MenuGroup newMenuGroup() {
			return MenuGroup.builder()
						    .menuGroupCode(this.menuGroupCode)
						    .menuGroupName(this.menuGroupName)
						    .description(this.description)
						    .build();	
		}
		
		public void modifyMenuGroup(MenuGroup menuGroup) {
			menuGroup.modifyEntity(this.menuGroupName, this.description);
		}
	}
}
