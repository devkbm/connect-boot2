package com.like.user.domain.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.like.dept.domain.model.Dept;
import com.like.dept.domain.repository.DeptRepository;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.repository.MenuRepository;
import com.like.user.boundary.UserDTO;
import com.like.user.boundary.UserDTO.SaveUser;
import com.like.user.domain.repository.UserRepository;

public class UserDTOAssembler {	
		
	
	public static User toEntity(UserDTO.SaveUser dto, UserRepository repository, MenuRepository menuRepository, DeptRepository deptRepository) {
		User user = repository.getUser(dto.getUserId());
		
		List<Authority> authorityList = repository.getAuthorityList(dto.getAuthorityList());		
		List<MenuGroup> menuGroupList = menuRepository.getMenuGroupList(dto.getMenuGroupList());
		Dept dept = null; 
		
		if ( dto.getDeptCode() != null ) {
			dept = deptRepository.getDept(dto.getDeptCode());
		}
		
		if (user == null) {
			user = UserDTOAssembler.createEntity(dto, dept, authorityList, menuGroupList);
		} else {
			user = UserDTOAssembler.mergeEntity(user, dto, dept, authorityList, menuGroupList);
		}
		
		return user;
	}
	
	
	public static User createEntity(UserDTO.SaveUser dto, Dept dept, List<Authority> authorityList, List<MenuGroup> menuGroupList) {
						
		return User.builder()
					.userId(dto.getUserId())										
					.name(dto.getName())
					.password(dto.getPassword())	
					.dept(dept)				
					.mobileNum(dto.getMobileNum())
					.email(dto.getEmail())
					.isEnabled(dto.getEnabled())	
					.authorities(authorityList)
					.menuGroupList(menuGroupList)					
					.build();
	}
	
	public static User mergeEntity(User entity, UserDTO.SaveUser dto, Dept dept, List<Authority> authorityList, List<MenuGroup> menuGroupList) {
		
		entity.userId		= nvl(dto.getUserId(), 		entity.userId);
		entity.name			= nvl(dto.getName(),	 	entity.name);
		entity.password		= nvl(dto.getPassword(), 	entity.password);
		entity.dept			= dept;
		entity.mobileNum	= nvl(dto.getMobileNum(),	entity.mobileNum);
		entity.email		= nvl(dto.getEmail(),		entity.email);
		entity.isEnabled	= nvl(dto.getEnabled(),		entity.isEnabled);
		entity.authorities 	= authorityList;
		entity.menuGroupList= menuGroupList;
		
		return entity;
	}		
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a가 NULL일 경우 b, 이외에는 a 리턴
	 */
	private static <T>T nvl(T a, T b) {		
		return a == null ? b : a;
	}
		
			
}
