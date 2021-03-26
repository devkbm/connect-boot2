package com.like.menu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.menu.boundary.MenuDTO;
import com.like.menu.boundary.MenuGroupDTO;
import com.like.menu.boundary.WebResourceDTO;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.WebResource;
import com.like.menu.domain.repository.MenuGroupRepository;
import com.like.menu.domain.repository.MenuRepository;
import com.like.menu.domain.repository.WebResourceRepository;
import com.like.menu.domain.repository.MenuRepository;

@Service
@Transactional
public class MenuCommandService {

	private MenuGroupRepository menuGroupRepository;
	private MenuRepository menuRepository;
	private WebResourceRepository webResourceRepository;
			
	public MenuCommandService(MenuGroupRepository menuGroupRepository
							 ,MenuRepository menuRepository
							 ,WebResourceRepository webResourceRepository) {
		this.menuGroupRepository = menuGroupRepository;
		this.menuRepository = menuRepository;
		this.webResourceRepository = webResourceRepository;
	}

	public MenuGroup getMenuGroup(String menuGroupCode) {
		return menuGroupRepository.findById(menuGroupCode).orElse(null);
	}
	
	public void saveMenuGroup(MenuGroup codeGroup) {
		menuGroupRepository.save(codeGroup);	
	}
	
	public void saveMenuGroup(MenuGroupDTO.SaveMenuGroup dto) {
		MenuGroup menuGroup = dto.getMenuGroupCode() != null ? menuGroupRepository.findById(dto.getMenuGroupCode()).orElse(null) : null; 			
		
		if (menuGroup == null) {
			menuGroup = dto.newMenuGroup();
		} else {
			dto.modifyMenuGroup(menuGroup);
		}
		
		menuGroupRepository.save(menuGroup);	
	}
	
	public void deleteMenuGroup(String menuGroupCode) {
		menuGroupRepository.deleteById(menuGroupCode);
	}
	
	public Menu getMenu(String menuCode) {
		return menuRepository.findById(menuCode).orElse(null);
	}
	
	public void saveMenu(Menu menu) throws Exception {			
		menuRepository.save(menu);		
	}
	
	public void saveMenu(MenuDTO.SaveMenu dto) {
		MenuGroup menuGroup = menuGroupRepository.findById(dto.getMenuGroupCode()).orElse(null);
		Menu menu = menuRepository.findById(dto.getMenuCode()).orElse(null);
		Menu parent = dto.getParentMenuCode() != null ? menuRepository.findById(dto.getParentMenuCode()).orElse(null) : null; 
		WebResource resource = dto.getResource() != null ? webResourceRepository.findById(dto.getResource()).orElse(null) : null;					
					
		if (menu == null) {
			menu = dto.newMenu(menuGroup, resource);
		} else {
			dto.modifyMenu(menu, parent, menuGroup, resource);
		}		
		
		menuRepository.save(menu);	
	}
	
	public void deleteMenu(String menuCode) {
		
		menuRepository.deleteById(menuCode);
	}		
	
}
