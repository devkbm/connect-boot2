package com.like.user.domain.model.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class AccountSpec implements Serializable {
		
	private static final long serialVersionUID = 5321100631628293509L;

	@Column(name="non_expired_yn")
	Boolean isAccountNonExpired = true;
		
	@Column(name="non_locked_yn")
	Boolean isAccountNonLocked = true;
		
	@Column(name="pass_non_expired_yn")
	Boolean isCredentialsNonExpired = true;
		
	@Column(name="enabled_yn")
	Boolean isEnabled = true;
	
	public AccountSpec(Boolean isAccountNonExpired
					  ,Boolean isAccountNonLocked
					  ,Boolean isCredentialsNonExpired
					  ,Boolean isEnabled) {
		
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;			
	}
}
