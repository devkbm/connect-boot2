package com.like.user.boundary;

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
import com.like.user.domain.model.QUser;
import com.querydsl.core.BooleanBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDTO {
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UserSave {
		
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
								
	}

}
