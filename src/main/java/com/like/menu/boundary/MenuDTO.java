package com.like.menu.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.enums.MenuType;
import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;

public class MenuDTO {
		
	@Data
	public static class MenuSave implements Serializable {
		
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
							
		public MenuSave() {}
		
		public MenuSave(Menu menu) {					
			this.createdDt 		= menu.getCreatedDt();
			this.createdBy 		= menu.getCreatedBy();
			this.modifiedDt 	= menu.getModifiedDt();
			this.modifiedBy 	= menu.getModifiedBy();
			this.menuGroupCode 	= menu.getMenuGroup().getMenuGroupCode();
			this.menuCode 		= menu.getMenuCode();
			this.menuName 		= menu.getMenuName();			
			this.menuType		= menu.getMenuType().toString();				
			this.sequence 		= menu.getSequence();
			this.level 			= menu.getLevel();
			this.parentMenuCode = menu.getParent() == null ? null : menu.getParent().getMenuCode();
			this.resource 		= menu.getResource() == null ? null : menu.getResource().getResourceCode();
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