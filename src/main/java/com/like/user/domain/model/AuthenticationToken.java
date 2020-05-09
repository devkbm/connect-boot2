package com.like.user.domain.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.like.menu.domain.model.MenuGroup;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AuthenticationToken {
	
	private String userId;
	private String userName;
	private String imageUrl;
    //private Collection<? extends GrantedAuthority> authorities;
	private List<String> authorities;
    private List<MenuGroup> menuGroupList;
    private String token;
       
    @Builder
    public AuthenticationToken(
    		String userId, 
    		String userName, 
    		String imageUrl,
    		List<String> collection,
    		List<MenuGroup> menuGroupList,
    		String token) {
    	this.userId = userId;
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.authorities = collection;
        this.menuGroupList = menuGroupList;
        this.token = token;
    }       
       
}
