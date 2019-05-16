package com.like.user.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.like.common.domain.AuditEntity;
import com.like.dept.domain.model.Dept;
import com.like.menu.domain.model.MenuGroup;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"authorities","comusermenugroup"})
@Entity
@Table(name = "comuser")
public class User extends AuditEntity implements UserDetails {
	
	private static final long serialVersionUID = 2601682947639908458L;

	@Id	
	@Column(name="user_id")
	String userId;
	
	@Column(name="user_name")
	String name;
	
	@JsonProperty(access = Access.WRITE_ONLY)	
	@Column(name="pwd")
	String password;	
		
	@Column(name="non_expired_yn")
	Boolean isAccountNonExpired = true;
		
	@Column(name="non_locked_yn")
	Boolean isAccountNonLocked = true;
		
	@Column(name="pass_non_expired_yn")
	Boolean isCredentialsNonExpired = true;
		
	@Column(name="enabled_yn")
	Boolean isEnabled = true;
	
	@Column(name="mobile_num")
	String mobileNum;
	
	@Column(name="email")
	String email;
				
	@Column(name="fk_file")
	String image;
	
	@OneToOne(optional = true)
	@JoinColumn(name = "dept_cd", nullable = true)
	Dept dept;
	
	@Singular(value="authorities")
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="comuserauthority",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="authority_name"))	
	List<Authority> authorities;
		
	@Singular(value="menuGroupList") 
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="comusermenugroup",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns=@JoinColumn(name="menu_group_code"))	
	List<MenuGroup> menuGroupList;		
		
	@Builder
	public User(String userId, String name, String password, Dept dept, String mobileNum, String email,
			Boolean isAccountNonExpired, Boolean isAccountNonLocked, Boolean isCredentialsNonExpired, 
			Boolean isEnabled, List<Authority> authorities,	List<MenuGroup> menuGroupList) {		
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.dept = dept;
		this.mobileNum = mobileNum;
		this.email = email;
		this.isAccountNonExpired = isAccountNonExpired == null ? true : isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked == null ? true : isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired == null ? true : isCredentialsNonExpired;
		this.isEnabled = isEnabled == null ? true : isEnabled;
		this.authorities = authorities;
		this.menuGroupList = menuGroupList;
	}	
	
	@Override
	@JsonIgnore	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
			
	@Override
	@JsonProperty("userId")
	public String getUsername() {		
		return userId;
	}

	@Override		
	public String getPassword() {
		return password;
	}		

	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
			
	public String getUserId() {
		return this.userId;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isVaild(String password) {
		return this.password.equals(password) ? true : false;
	}		
	
	public List<Authority> getAuthorityList() {
		return authorities;
	}
	
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;		
	}
	
	public void addAuthoritiy(Authority authority) {
		if (this.authorities == null) {
			this.authorities = new ArrayList<>();
		}
		
		this.authorities.add(authority);
	}	
			
	public List<MenuGroup> getMenuGroupList() {
		return menuGroupList;
	}
					
	public void setMenuGroupList(List<MenuGroup> menuGroupList) {
		this.menuGroupList = menuGroupList;
	}
	
	public void changePassword(String password) {
		this.password = password;
	}
	
	/**
	 * 비밀번호를 초기화한다. 
	 * 초기화 비밀번호 : 12345678
	 */
	public void initPassword() {
		this.password = "12345678";	
	}
	
	public void ChangeImage(String imageFileInfo) {
		this.image = imageFileInfo;
	}
	
	public String getImage() {
		return this.image;
	}
				
	
}
