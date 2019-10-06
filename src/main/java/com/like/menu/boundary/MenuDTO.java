package com.like.menu.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.QMenu;
import com.like.menu.domain.model.QMenuGroup;
import com.like.menu.domain.model.WebResource;
import com.like.menu.domain.model.enums.MenuType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class MenuDTO {
		
	public static SaveMenu convertDTO(Menu menu) {
						
		return SaveMenu.builder()
				   	   .createdDt(menu.getCreatedDt())
				   	   .createdBy(menu.getCreatedBy())
				   	   .modifiedDt(menu.getModifiedDt())
				   	   .modifiedBy(menu.getModifiedBy())
				   	   .menuGroupCode(menu.getMenuGroup().getMenuGroupCode())
				   	   .menuCode(menu.getMenuCode())
				   	   .menuName(menu.getMenuName())
				   	   .menuType(menu.getMenuType().toString())
				   	   .sequence(menu.getSequence())
				   	   .level(menu.getLevel())
				   	   .parentMenuCode(menu.getParent() == null ? null : menu.getParent().getMenuCode())
				   	   .resource(menu.getResource() == null ? null : menu.getResource().getResourceCode())
				   	   .build();
	}
	
	@Data
	public static class SearchMenu implements Serializable {
		
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
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SaveMenu implements Serializable {
		
		private static final long serialVersionUID = 2421325619239144951L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotEmpty
		private String menuGroupCode;
				
		@NotEmpty
		private String menuCode;
			
		@NotEmpty
		private String menuName;
			
		private String parentMenuCode;
		
		private String menuType;
			
		private long sequence;
				
		private long level;
		
		private String resource;												
		
		public Menu newMenu(MenuGroup menuGroup, WebResource resource) {
			return Menu.builder()
					   .menuGroup(menuGroup)
					   .menuCode(this.menuCode)
					   .menuName(this.menuName)
					   .menuType(MenuType.valueOf(this.menuType))
					   .sequence(this.sequence)
					   .level(this.level)					   
					   .resource(resource)
					   .build();
		}
		
		public void modifyMenu(Menu menu, Menu parentMenu, MenuGroup menuGroup, WebResource resource) {
			menu.modifyEntity(this.menuName
					         ,MenuType.valueOf(this.menuType)
					         ,this.sequence
					         ,this.level
					         ,parentMenu
					         ,menuGroup
					         ,resource);
			
		}
	}
	
	@Data
	public static class MenuHierarchy implements Serializable {
					
		private static final long serialVersionUID = 6846227958954258462L;

		private String menuGroupCode;
			
		private String key;
		
		private String title;
		
		private String parentMenuCode;
			
		private String menuType;
		
		private Long sequence;
			
		private Long level;
					
		private String url;
						
		private boolean expanded;
		
		private boolean selected;
		
		private boolean isLeaf;
		
		private List<MenuHierarchy> children;

		@QueryProjection
		public MenuHierarchy(String menuGroupCode, String key, String title, String parentMenuCode,
				MenuType menuType, Long sequence, Long level, String url, boolean isLeaf) {		
			this.menuGroupCode = menuGroupCode;
			this.key = key;
			this.title = title;
			this.parentMenuCode = parentMenuCode;
			this.menuType = menuType.toString();
			this.sequence = sequence;
			this.level = level;
			this.url = url;
			this.isLeaf = isLeaf;		
			this.expanded = false;
			this.selected = false;
		}
		
		
		
		
	}
}
