package com.like.menu.domain.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.like.common.domain.AuditEntity;
import com.like.menu.domain.model.enums.MenuType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true, exclude = {"menuGroup"})
@Entity
@Getter
@Table(name = "commenu")
@EntityListeners(AuditingEntityListener.class)
public class Menu extends AuditEntity implements Serializable {
			
	private static final long serialVersionUID = -6069806707460127190L;

	@Id
	@Column(name = "menu_code")
	String menuCode;
	
	@Column(name="menu_name")
	String menuName; 		
				
	@Enumerated(EnumType.STRING)
	@JsonFormat(shape = JsonFormat.Shape.OBJECT)
	@Column(name="menu_type")
	MenuType menuType;
	
	@Column(name="seq")
	long sequence;
	
	@Column(name="lvl")
	long level;
	
	@OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="p_menu_code", nullable = true )
	Menu parent;
			 				
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_group_code", nullable=false, updatable=false)
	MenuGroup menuGroup = new MenuGroup();
	
	@OneToOne(optional=true)
	@JoinColumn(name = "resource_code", nullable=true)
	WebResource resource = new WebResource();
		
	@Builder
	public Menu(@NonNull MenuGroup menuGroup,
				String menuCode, 
				String menuName, 				 			
				MenuType menuType, 
				long sequence,
				long level, 				
				WebResource resource) {
		
		this.menuCode = menuCode;
		this.menuName = menuName;			
		this.menuType = menuType;
		this.sequence = sequence;
		this.level = level;
		this.menuGroup = menuGroup;
		this.resource = resource;
	}
							
	public void setMenuGroup(MenuGroup menuGroup) {
		this.menuGroup = menuGroup;
	}
	
	public void registerProgram(WebResource resource) {
		this.resource = resource;
	}
			
	/*public Menu updateEntity(MenuDTO.MenuSave dto) {
		this.menuCode	= dto.getMenuCode() != null ? dto.getMenuCode() : this.menuCode;
		this.menuName 	= dto.getMenuName() != null ? dto.getMenuName() : this.menuName;
		this.menuType	= MenuType.valueOf(dto.getMenuType());
		this.sequence 	= dto.getSequence();
		this.level 		= dto.getLevel();
		this.parentMenuCode = dto.getParentMenuCode() != null ? dto.getParentMenuCode() : this.parentMenuCode;
		
		return this;
	}*/

}
