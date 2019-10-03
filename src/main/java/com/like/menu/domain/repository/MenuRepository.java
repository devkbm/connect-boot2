package com.like.menu.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.menu.boundary.MenuDTO;
import com.like.menu.boundary.MenuGroupDTO;
import com.like.menu.boundary.WebResourceDTO;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.WebResource;

@Repository
public interface MenuRepository {
	
	MenuGroup getMenuGroup(String menuGroupCode);
	
	List<MenuGroup> getMenuGroupList(MenuGroupDTO.SearchMenuGroup condition);
	
	List<MenuGroup> getMenuGroupList(String likeMenuGroupName);
	
	List<MenuGroup> getMenuGroupList(List<String> menuGroupNameList);
	
	void saveMenuGroup(MenuGroup menuGroup);
	
	void deleteMenuGroup(String menuGroupCode);
	
	
	Menu getMenu(String menuCode);
			
	List<Menu> getMenuList(MenuDTO.SearchMenu condition);
			
	void saveMenu(Menu menu);
	
	void deleteMenu(String menuCode);	
	
	
	WebResource getResource(String resouceCode);
	
	List<WebResource> getResourceList(WebResourceDTO.SearchWebResource condition);
	
	void saveResource(WebResource resource);
	
	void deleteResource(String resouceCode);
	
}
