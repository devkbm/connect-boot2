package com.like.menu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.WebResource;
import com.like.menu.infra.jparepository.MenuJpaRepository;

@Service
@Transactional
public class MenuCommandService {

	@Resource(name="menuJpaRepository")
	private MenuJpaRepository menuJpaRepository;
			
	public void saveMenuGroup(MenuGroup codeGroup) {
		menuJpaRepository.saveMenuGroup(codeGroup);	
	}
	
	public void deleteMenuGroup(String menuGroupCode) {
		menuJpaRepository.deleteMenuGroup(menuGroupCode);
	}
	
	public void saveMenu(Menu menu, String menuGroupCode) throws Exception {
		MenuGroup menuGroup = menuJpaRepository.getMenuGroup(menuGroupCode);		
		
		if ( menuGroup == null ) {
			throw new IllegalArgumentException(" MenuGroup Entity(" + menuGroupCode + ") is not exist");			
		}
		
		menuJpaRepository.saveMenu(menu,menuGroup);		
	}
	
	public void deleteMenu(String menuCode) {
		menuJpaRepository.deleteMenu(menuCode);
	}
	
	public void saveWebResource(WebResource resource) {				
		menuJpaRepository.saveResource(resource);
	}
	
	public void deleteWebResource(String resourceCode) {
		menuJpaRepository.deleteResource(resourceCode);
	}
	
}
