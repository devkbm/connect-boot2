package com.like.menu.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.menu.boundary.MenuDTO;
import com.like.menu.boundary.MenuGroupDTO;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.infra.jparepository.MenuQueryJpaRepository;

@Service
@Transactional(readOnly=true)
public class MenuQueryService {

	private MenuQueryJpaRepository menuJpaRepository;

	public MenuQueryService(MenuQueryJpaRepository menuJpaRepository) {
		this.menuJpaRepository = menuJpaRepository;
	}
		
	public List<MenuGroup> getMenuGroupList(MenuGroupDTO.SearchMenuGroup condition) {
		return menuJpaRepository.getMenuGroupList(condition);
	}
	
	public List<MenuGroup> getMenuGroupList(String likeMenuGroupName) {
		return menuJpaRepository.getMenuGroupList(likeMenuGroupName);
	}				
	
	public List<Menu> getMenuList(MenuDTO.SearchMenu condition) {
		return menuJpaRepository.getMenuList(condition);
	}
	
	public List<MenuDTO.MenuHierarchy> getMenuHierachy(String menuGroupCode) {
		List<MenuDTO.MenuHierarchy> rootList = menuJpaRepository.getMenuRootList(menuGroupCode);
		
		return menuJpaRepository.getMenuHierarchyDTO(rootList);
	}
		
}
