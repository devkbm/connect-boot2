package com.like.menu.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebControllerUtil;
import com.like.menu.boundary.EnumDTO;
import com.like.menu.boundary.MenuDTO;
import com.like.menu.boundary.MenuGroupDTO;
import com.like.menu.boundary.MenuGroupDTO.SaveMenuGroup;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.enums.MenuType;
import com.like.menu.service.MenuQueryService;

@RestController
public class MenuQueryController {

	private MenuQueryService menuQueryService;
	
	public MenuQueryController(MenuQueryService menuQueryService) {
		this.menuQueryService = menuQueryService;		
	}
	
	@GetMapping("/common/menutest/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachyTest(@PathVariable(value="menuGroupCode") String menuGroupCode) {				
		
		List<MenuDTO.MenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 							
		
		return WebControllerUtil.getResponse(menuGroup											
											,String.format("%d 건 조회되었습니다.", menuGroup.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/common/menuhierarchy/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@PathVariable(value="menuGroupCode") String menuGroupCode) {				
		
		List<MenuDTO.MenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 										
		
		return WebControllerUtil.getResponse(menuGroup											
											,String.format("%d 건 조회되었습니다.", menuGroup.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/common/menugroup")
	public ResponseEntity<?> getMenuGroupList(MenuGroupDTO.SearchMenuGroup dto) {				
		
		List<MenuGroup> list = menuQueryService.getMenuGroupList(dto); 							
		
		List<MenuGroupDTO.SaveMenuGroup> dtoList = list.stream().map(e -> SaveMenuGroup.convert(e)).collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/common/menu")
	public ResponseEntity<?> getMenuList(MenuDTO.SearchMenu dto) {				
		
		List<Menu> list = menuQueryService.getMenuList(dto);			
		
		List<MenuDTO.SaveMenu> dtoList = list.stream().map(e -> MenuDTO.SaveMenu.convert(e)).collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/common/menu/menutype")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<EnumDTO> list = new ArrayList<EnumDTO>();
		
		for (MenuType menuType : MenuType.values()) {
			EnumDTO dto = new EnumDTO(menuType.toString(), menuType.getName());
			list.add(dto);
		}
		
		return WebControllerUtil.getResponse(list											
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
}
