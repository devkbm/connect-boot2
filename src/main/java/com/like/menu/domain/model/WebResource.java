package com.like.menu.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.common.domain.AuditEntity;
import com.like.menu.dto.WebResourceDTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@Entity
@Table(name = "comresource")
@EntityListeners(AuditingEntityListener.class)
public class WebResource extends AuditEntity implements Serializable{
	
	private static final long serialVersionUID = -3215673518471434299L;

	@Id
	@Column(name="resource_code")
	private String resourceCode;
	
	@Column(name="resource_name")
	private String resourceName; 
		
	@Column(name="resource_type")
	private String resourceType; 
	
	@Column(name="url")
	private String url;
	
	@Column(name="description")
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy="resource", fetch=FetchType.LAZY)          
    List<Menu> menuList = new ArrayList<Menu>();
		
	@Builder
	public WebResource(String resourceCode, String resourceName, String resourceType, String url, String description) {
		super();
		this.resourceCode = resourceCode;
		this.resourceName = resourceName;
		this.resourceType = resourceType;
		this.url = url;
		this.description = description;
	}	
	
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
	public void registerMenu(Menu menu) {
		this.menuList.add(menu);
	}

	
	
	/*public WebResource updateEntity(ProgramDTO.ProgramSave dto) {
		this.programCode 	= dto.getProgramCode() != null ? dto.getProgramCode() : this.programCode ;
		this.programName 	= dto.getProgramName() != null ? dto.getProgramName() : this.programName ;
		this.url			= dto.getUrl() != null ? dto.getUrl() : this.url ;
		this.description	= dto.getDescription() != null ? dto.getDescription() : this.description ;
		
		return this;
	}*/
			
}
