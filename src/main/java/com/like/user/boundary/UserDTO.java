package com.like.user.boundary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.like.common.validation.annotation.Id;
import com.like.dept.domain.model.Dept;
import com.like.menu.domain.model.MenuGroup;
import com.like.user.boundary.UserDTO.SaveUser;
import com.like.user.domain.model.Authority;
import com.like.user.domain.model.QUser;
import com.like.user.domain.model.User;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDTO {
	
	public static UserDTO.SaveUser convertDTO(User entity) throws FileNotFoundException, IOException {					
		
		SaveUser dto = SaveUser.builder()
								.createdDt(entity.getCreatedDt())
								.createdBy(entity.getCreatedBy())
								.modifiedDt(entity.getModifiedDt())
								.modifiedBy(entity.getModifiedBy())
								.userId(entity.getUserId())
								.name(entity.getName())
								.password(entity.getPassword())
								.deptCode(entity.getDept() == null ? null : entity.getDept().getDeptCode())
								.mobileNum(entity.getMobileNum())
								.email(entity.getEmail())
								.enabled(entity.isEnabled())								
								.authorityList(entity.getAuthorityList()
													.stream()
													.map(auth -> auth.getAuthority())
													.collect(Collectors.toList()))
								.menuGroupList(entity.getMenuGroupList()
													.stream()
													.map(menuGroup -> menuGroup.getMenuGroupCode())
													.collect(Collectors.toList()))
								.build();
		
		return dto;
	}

	@Data
	public static class SearchUser implements Serializable {

		private static final long serialVersionUID = -7886731992928427538L;

		private final QUser qUser = QUser.user;
		
		String userId;
		
		String name;
		
		String deptCode;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeUserId(this.userId))
			 	   .and(likeUserName(this.name))
			 	   .and(equalDeptCode(this.deptCode));						
			
			return builder;
		}
		
		private BooleanExpression likeUserId(String userId) {
			if (StringUtils.isEmpty(userId)) {
				return null;				
			}
						
			return qUser.userId.like("%"+userId+"%");
		}
		
		private BooleanExpression likeUserName(String name) {
			if (StringUtils.isEmpty(name)) {
				return null;
			}
						
			return qUser.name.like("%"+name+"%");
		}
		
		private BooleanExpression equalDeptCode(String deptCode) {
			if (StringUtils.isEmpty(deptCode)) {
				return null;
			}
			
			return qUser.dept.deptCode.eq(deptCode);
		}
	}
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SaveUser {
		
		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotBlank(message="아이디를 입력해주세요")
		@Size(min=1, max=20, message="1자 이상 20자 이하의 아이디만 사용 가능합니다")
		@Pattern(regexp="^[A-Za-z0-9+]*$",message="영문,숫자로 이루어진 아이디만 사용 가능합니다")
		@Id(message="이미 가입한 아이디입니다")
		String userId;
			
		String name;
			
		String password;	
		
		String deptCode;
		
		String mobileNum;
		
		String email;
		
		String imageBase64;
				
		Boolean accountNonExpired;
			
		Boolean accountNonLocked;
			
		Boolean credentialsNonExpired;
			
		Boolean enabled;
						
		List<String> authorityList = new ArrayList<String>();

		List<String> menuGroupList = new ArrayList<String>(); 
		
		public User newUser(Dept dept, List<Authority> authorityList, List<MenuGroup> menuGroupList) {
			return User.builder()
					   .userId(this.userId)										
					   .name(this.name)
					   .password(this.password)	
					   .dept(dept)				
					   .mobileNum(this.mobileNum)
					   .email(this.email)
					   .isEnabled(this.enabled)	
					   .authorities(authorityList)
					   .menuGroupList(menuGroupList)					
					   .build();
			
		}
		
		public void modifyUser(User user, Dept dept, List<Authority> authorityList, List<MenuGroup> menuGroupList) {
			user.modifyEntity(name
							 ,password
							 ,enabled
							 ,mobileNum
							 ,email							  
							 ,dept
							 ,authorityList
							 ,menuGroupList);	
		}
								
	}

	

}
