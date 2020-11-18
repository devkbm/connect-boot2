package com.like.menu.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.menu.boundary.EnumDTO;
import com.like.menu.boundary.MenuDTO;
import com.like.menu.boundary.MenuGroupDTO;
import com.like.menu.boundary.WebResourceDTO;
import com.like.menu.boundary.MenuGroupDTO.SaveMenuGroup;
import com.like.menu.boundary.WebResourceDTO.SaveWebResource;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.WebResource;
import com.like.menu.domain.model.enums.MenuType;
import com.like.menu.service.MenuCommandService;
import com.like.menu.service.MenuQueryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MenuController {
	
	private MenuCommandService menuCommandService;
	
	private MenuQueryService menuQueryService;
			
	public MenuController(MenuCommandService menuCommandService
						 ,MenuQueryService menuQueryService) {
		this.menuCommandService = menuCommandService;
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
		
		log.info(SecurityContextHolder.getContext().getAuthentication().getName());
		
		return WebControllerUtil.getResponse(menuGroup											
											,String.format("%d 건 조회되었습니다.", menuGroup.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/common/menugroup/{id}")
	public ResponseEntity<?> getMenuGroup(@PathVariable(value="id") String menuGroupCode) {				
		
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupCode);
		
		MenuGroupDTO.SaveMenuGroup dto = SaveMenuGroup.convert(menuGroup);
								
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0)
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
		
	@PostMapping("/common/menugroup/{id}")
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO.SaveMenuGroup dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 							
																			
		menuCommandService.saveMenuGroup(dto);			
										 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@DeleteMapping("/common/menugroup/{id}")
	public ResponseEntity<?> delCodeGroup(@PathVariable(value="id") String menuGroupCode) {				
												
		menuCommandService.deleteMenuGroup(menuGroupCode);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	
	@GetMapping("/common/menu/{menucode}")
	public ResponseEntity<?> getMenu(@PathVariable(value="menucode") String menuCode) {				
		
		Menu menu = menuQueryService.getMenu(menuCode); 		
		
		MenuDTO.SaveMenu dto = MenuDTO.SaveMenu.convert(menu);			
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0)
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
		
	@PostMapping("/common/menu/{menucode}")
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO.SaveMenu dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors()) {
			//throw new ControllerException("오류");
			log.info(result.getAllErrors().toString());
		} 					
									
		menuCommandService.saveMenu(dto);																			
														 				
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/common/menu/{id}")
	public ResponseEntity<?> delMenu(@PathVariable(value="id") String menuCode) {				
												
		menuCommandService.deleteMenu(menuCode);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	
	@GetMapping("/common/webresource")
	public ResponseEntity<?> getWebResourceList(WebResourceDTO.SearchWebResource condition) {							 			
		
		List<WebResource> list = menuQueryService.getResourceList(condition);
										
		List<WebResourceDTO.SaveWebResource> dtoList = list.stream().map(e -> SaveWebResource.convertDTO(e)).collect(Collectors.toList());
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK); 
	}
	
	@GetMapping("/common/webresource/{code}")
	public ResponseEntity<?> getResource(@PathVariable(value="code") String code) {				
		
		WebResource resource = menuQueryService.getResource(code); 							
		
		WebResourceDTO.SaveWebResource dto = SaveWebResource.convertDTO(resource);
		
		return WebControllerUtil.getResponse(dto											
											,String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0)
											,HttpStatus.OK);
	}
		
	@PostMapping("/common/webresource")
	public ResponseEntity<?> saveResource(@RequestBody @Valid WebResourceDTO.SaveWebResource dto, BindingResult result) throws Exception {
										
		if ( result.hasErrors()) {
			throw new ControllerException(result.getAllErrors().toString());
		} 
																	
		menuCommandService.saveWebResource(dto);																						
										 					
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@DeleteMapping("/common/webresource/{code}")
	public ResponseEntity<?> delResource(@PathVariable(value="code") String code) {				
												
		menuCommandService.deleteWebResource(code);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	
}
