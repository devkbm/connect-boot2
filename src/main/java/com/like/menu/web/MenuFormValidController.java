package com.like.menu.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.util.WebControllerUtil;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.service.MenuCommandService;

@RestController
public class MenuFormValidController {

	private MenuCommandService menuQueryService;
	
	public MenuFormValidController(MenuCommandService menuQueryService) {
		this.menuQueryService = menuQueryService;		
	}

	@GetMapping("/common/menugroup/{id}/check")
	public ResponseEntity<?> getMenuGroupValid(@PathVariable(value="id") String menuGroupCode) {							
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupCode);
		Boolean isValid = menuGroup == null ? true : false;				
								
		return WebControllerUtil
				.getResponse(isValid
							,String.format("%d 건 조회되었습니다.", menuGroup != null ? 1 : 0)
							,HttpStatus.OK);
	}
	
	@GetMapping("/common/menu/{menucode}/check")
	public ResponseEntity<?> getMenuValid(			
			@PathVariable(value="menucode") String menuCode) {						
		Menu menu = menuQueryService.getMenu(menuCode); 		
		Boolean isValid = menu == null ? true : false;			
		
		return WebControllerUtil
				.getResponse(isValid
							,String.format("%d 건 조회되었습니다.", menu != null ? 1 : 0)
							,HttpStatus.OK);
	}
	
}
