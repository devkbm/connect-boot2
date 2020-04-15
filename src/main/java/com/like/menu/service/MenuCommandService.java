package com.like.menu.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.menu.boundary.MenuDTO;
import com.like.menu.boundary.MenuGroupDTO;
import com.like.menu.boundary.WebResourceDTO;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.WebResource;
import com.like.menu.domain.repository.MenuRepository;

@Service
@Transactional
public class MenuCommandService {

	private MenuRepository menuRepository;
			
	public MenuCommandService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	public void saveMenuGroup(MenuGroup codeGroup) {
		menuRepository.saveMenuGroup(codeGroup);	
	}
	
	public void saveMenuGroup(MenuGroupDTO.SaveMenuGroup dto) {
		MenuGroup menuGroup = null;
		
		if (dto.getMenuGroupCode() != null) {
			menuGroup = menuRepository.getMenuGroup(dto.getMenuGroupCode());
		}
		
		if (menuGroup == null) {
			menuGroup = dto.newMenuGroup();
		} else {
			dto.modifyMenuGroup(menuGroup);
		}
		
		menuRepository.saveMenuGroup(menuGroup);	
	}
	
	public void deleteMenuGroup(String menuGroupCode) {
		menuRepository.deleteMenuGroup(menuGroupCode);
	}
	
	public void saveMenu(Menu menu) throws Exception {			
		menuRepository.saveMenu(menu);		
	}
	
	public void saveMenu(MenuDTO.SaveMenu dto) {
		MenuGroup menuGroup = menuRepository.getMenuGroup(dto.getMenuGroupCode());
		Menu menu = menuRepository.getMenu(dto.getMenuCode());
		Menu parent = null; 
		WebResource resource = null;
		
		if (dto.getParentMenuCode() != null) {
			parent = menuRepository.getMenu(dto.getParentMenuCode());
		}
		
		
		if (dto.getResource() != null) {
			resource = menuRepository.getResource(dto.getResource());
		}
				
		if (menu == null) {
			menu = dto.newMenu(menuGroup, resource);
		} else {
			dto.modifyMenu(menu, parent, menuGroup, resource);
		}		
		
		menuRepository.saveMenu(menu);	
	}
	
	public void deleteMenu(String menuCode) {
		
		menuRepository.deleteMenu(menuCode);
	}
	
	public void saveWebResource(WebResource resource) {				
		menuRepository.saveResource(resource);
	}
	
	public void saveWebResource(WebResourceDTO.SaveWebResource dto) {	
		WebResource resource = menuRepository.getResource(dto.getResourceCode());
		
		if (resource == null) {
			resource = dto.newWebResource();
		} else {
			dto.modifyWebResource(resource);
		}
		
		menuRepository.saveResource(resource);
	}
	
	public void deleteWebResource(String resourceCode) {
		menuRepository.deleteResource(resourceCode);
	}
	
}
