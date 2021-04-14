package com.like.user.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.like.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name = "comauthority")
public class Authority extends AuditEntity implements GrantedAuthority {

	private static final long serialVersionUID = 5255280527856714047L;
	
	@Id
	@Column(name="authority_name")
	String authorityName;
	
	@Column(name="description")
	String description;	
	
	public Authority(String authorityName, String description) {		
		this.authorityName = authorityName;
		this.description = description;
	}	
	
	public void modifyEntity(String description) {
		this.description = description;
	}
	
	@Override
	public String getAuthority() {
		return this.authorityName;
	}

	public String getDescription() {
		return description;
	}

	
	
}
