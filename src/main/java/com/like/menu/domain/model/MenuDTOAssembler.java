package com.like.menu.domain.model;

import com.like.menu.domain.model.enums.MenuType;
import com.like.menu.domain.repository.MenuRepository;
import com.like.menu.dto.MenuDTO;
import com.like.menu.dto.MenuGroupDTO;

public class MenuDTOAssembler {

	public static MenuGroup toEntity(MenuRepository repository, MenuGroupDTO.MenuGroupSave dto) {
		MenuGroup entity = repository.getMenuGroup(dto.getMenuGroupCode());
		
		if (entity == null) {
			entity = MenuGroup.builder()
							  .menuGroupCode(dto.getMenuGroupCode())
							  .menuGroupName(dto.getMenuGroupName())
							  .description(dto.getDescription())
							  .build();							
		} else {
			entity.menuGroupName 	= nvl(dto.getMenuGroupName(),	entity.menuGroupName);
			entity.description 		= nvl(dto.getDescription(),		entity.description);
		}
								
		return entity;
	}	
	
	public static Menu toEntity(MenuRepository repository, MenuDTO.MenuSave dto) {
		MenuGroup menuGroup = repository.getMenuGroup(dto.getMenuGroupCode());
		Menu entity = repository.getMenu(dto.getMenuCode());
		WebResource resource = repository.getResource(dto.getResource());
				
		if (entity == null) {
			entity = Menu.builder()
						 .menuGroup(menuGroup)
						 .menuCode(dto.getMenuCode())
						 .menuName(dto.getMenuName())
						 .menuType(MenuType.valueOf(dto.getMenuType()))
						 .sequence(dto.getSequence())
						 .resource(resource)
						 .build();
		} else {
			entity.menuName = nvl(dto.getMenuName(), 	entity.menuName);
			entity.menuType = nvl(MenuType.valueOf(dto.getMenuType()), entity.menuType);
			entity.sequence = nvl(dto.getSequence(), 	entity.sequence);
			entity.resource = nvl(resource, 			entity.resource);
		}
		
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
