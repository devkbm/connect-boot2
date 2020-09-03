package com.like.user.domain.model.vo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Embeddable
public class LoginSpec {
	
	@Column(name="non_expired_yn")
	Boolean isAccountNonExpired = true;
		
	@Column(name="non_locked_yn")
	Boolean isAccountNonLocked = true;
		
	@Column(name="pass_non_expired_yn")
	Boolean isCredentialsNonExpired = true;
		
	@Column(name="enabled_yn")
	Boolean isEnabled = true;
	
	public LoginSpec(Boolean isAccountNonExpired
					,Boolean isAccountNonLocked
					,Boolean isCredentialsNonExpired
					,Boolean isEnabled) {
		
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;			
	}
}
