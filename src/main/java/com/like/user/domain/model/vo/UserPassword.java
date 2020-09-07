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
public class UserPassword implements Serializable {
	
	private static final long serialVersionUID = 5831655386795107265L;
	
	@Column(name="pwd")
	String password;
		
	public UserPassword(String rawPassword) {
		this.password = rawPassword;
	}
	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	
}
