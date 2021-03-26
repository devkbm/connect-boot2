package com.like.menu.domain.repository;

import java.util.List;

import com.like.menu.boundary.MenuDTO;
import com.like.menu.boundary.MenuGroupDTO;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;

public interface MenuQueryRepository {

	List<MenuGroup> getMenuGroupList(MenuGroupDTO.SearchMenuGroup condition);
	
	List<MenuGroup> getMenuGroupList(String likeMenuGroupName);
	
	List<Menu> getMenuList(MenuDTO.SearchMenu condition);
	
}
