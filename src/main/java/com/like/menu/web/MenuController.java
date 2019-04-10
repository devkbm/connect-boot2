package com.like.menu.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.WebResource;
import com.like.menu.domain.model.enums.MenuType;
import com.like.menu.dto.EnumDTO;
import com.like.menu.dto.MenuDTO;
import com.like.menu.dto.MenuGroupDTO;
import com.like.menu.dto.WebResourceDTO;
import com.like.menu.service.MenuCommandService;
import com.like.menu.service.MenuQueryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MenuController {

	@Resource
	private MenuCommandService menuCommandService;
	
	@Resource
	private MenuQueryService menuQueryService;
			
	@GetMapping("/common/menugroup/{id}")
	public ResponseEntity<?> getMenuGroup(@PathVariable(value="id") String menuGroupCode) {				
		
		MenuGroup menuGroup = menuQueryService.getMenuGroup(menuGroupCode); 		
								
		return WebControllerUtil.getResponse(menuGroup, 
				menuGroup != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@GetMapping("/common/menutest/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachyTest(@PathVariable(value="menuGroupCode") String menuGroupCode) {				
		
		List<MenuDTO.MenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 							
		
		return WebControllerUtil.getResponse(menuGroup, 
				menuGroup.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup.size()), 
				HttpStatus.OK);
	}
	
	@GetMapping("/common/menuhierarchy/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@PathVariable(value="menuGroupCode") String menuGroupCode) {				
		
		List<MenuDTO.MenuHierarchy> menuGroup = menuQueryService.getMenuHierachy(menuGroupCode); 							
		
		return WebControllerUtil.getResponse(menuGroup, 
				menuGroup.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", menuGroup.size()), 
				HttpStatus.OK);
	}
	
	@GetMapping("/common/menugroup")
	public ResponseEntity<?> getMenuGroupList(MenuGroupDTO.QueryCondition dto) {				
		
		List<MenuGroup> list = menuQueryService.getMenuGroupList(dto); 							
		
		return WebControllerUtil.getResponse(list,
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/common/menugroup/{id}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenuGroup(@Valid @RequestBody MenuGroupDTO.MenuGroupSave dto, BindingResult result) {				
		
		if ( result.hasErrors()) {			
			throw new ControllerException(result.getAllErrors().toString());
		} 
		
		MenuGroup menuGroup = menuQueryService.getMenuGroup(dto.getMenuGroupCode());
		
		if (menuGroup == null) {
			menuGroup = new MenuGroup(dto.getMenuGroupCode(), dto.getMenuGroupName(), dto.getDescription());
		} else {
			menuGroup = menuGroup.updateEntity(dto);
		}					
																			
		menuCommandService.saveMenuGroup(menuGroup);			
										 					
		return WebControllerUtil.getResponse(null,
				menuGroup != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", menuGroup != null ? 1 : 0), 
				HttpStatus.OK);
	}
		
	@DeleteMapping("/common/menugroup/{id}")
	public ResponseEntity<?> delCodeGroup(@PathVariable(value="id") String menuGroupCode) {				
												
		menuCommandService.deleteMenuGroup(menuGroupCode);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	
	@GetMapping("/common/menu/{menucode}")
	public ResponseEntity<?> getMenu(@PathVariable(value="menucode") String menuCode) {				
		
		Menu menu = menuQueryService.getMenu(menuCode); 		
		
		MenuDTO.MenuSave dto = new MenuDTO.MenuSave(menu);			
		
		return WebControllerUtil.getResponse(dto, 
				dto != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", dto != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@GetMapping("/common/menu")
	public ResponseEntity<?> getMenuList(MenuDTO.QueryCondition dto) {				
		
		List<Menu> list = menuQueryService.getMenuList(dto);														 						
		
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
	
	@GetMapping("/common/menu/menutype")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<EnumDTO> list = new ArrayList<EnumDTO>();
		
		for (MenuType menuType : MenuType.values()) {
			EnumDTO dto = new EnumDTO(menuType.toString(), menuType.getName());
			list.add(dto);
		}				 					
		
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
	
	
	@RequestMapping(value={"/common/menu/{menucode}"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveMenu(@RequestBody @Valid MenuDTO.MenuSave dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors()) {
			//throw new ControllerException("오류");
			log.info(result.getAllErrors().toString());
		} 
		
		Menu menu = menuQueryService.getMenu(dto.getMenuCode());			
						
		if ( menu == null ) {								
			MenuGroup menuGroup = menuQueryService.getMenuGroup(dto.getMenuGroupCode());						
			WebResource program = null;
			
			if (StringUtils.hasText(dto.getResource())) {
				program = menuQueryService.getResource(dto.getResource());;
			}					
			
			menu = new Menu(dto.getMenuCode(), 
							dto.getMenuName(),
							dto.getParentMenuCode(),
							MenuType.valueOf(dto.getMenuType()), 
							dto.getSequence(), 
							dto.getLevel(),
							menuGroup,
							program);
		} else {
			menu.updateEntity(dto);
			
			if (dto.getResource() != null) {
				WebResource program = menuQueryService.getResource(dto.getResource());
				menu.registerProgram(program);
			}
		}			
		
		menuCommandService.saveMenu(menu, dto.getMenuGroupCode());																			
														 				
		return WebControllerUtil.getResponse(null,
				menu != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", menu != null ? 1 : 0),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/common/menu/{id}")
	public ResponseEntity<?> delMenu(@PathVariable(value="id") String menuCode) {				
												
		menuCommandService.deleteMenu(menuCode);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	
	@GetMapping("/common/webresource")
	public ResponseEntity<?> getWebResourceList(WebResourceDTO.QueryCondition condition) {							 			
		
		List<WebResource> list = menuQueryService.getResourceList(condition);
										
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK); 
	}
	
	@GetMapping("/common/webresource/{code}")
	public ResponseEntity<?> getResource(@PathVariable(value="code") String code) {				
		
		WebResource resource = menuQueryService.getResource(code); 							
		
		return WebControllerUtil.getResponse(resource, 
				resource != null ? 1 : 0, 
				true, 
				String.format("%d 건 조회되었습니다.", resource != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/common/webresource"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveResource(@RequestBody @Valid WebResourceDTO.ResourceSave dto, BindingResult result) throws Exception {
										
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 
		
		WebResource resource = menuQueryService.getResource(dto.getResourceCode());							
		
		resource = WebResource.builder()
								.resourceCode(dto.getResourceCode())
								.resourceName(dto.getResourceName())
								.resourceType(dto.getResourceType())
								.url(dto.getUrl())
								.description(dto.getDescription())
								.build();
					
		menuCommandService.saveWebResource(resource);																						
										 					
		return WebControllerUtil.getResponse(null,
				resource != null ? 1 : 0, 
				true, 
				String.format("%d 건 저장되었습니다.", resource != null ? 1 : 0), 
				HttpStatus.OK);
	}
	
	@DeleteMapping("/common/webresource/{code}")
	public ResponseEntity<?> delResource(@PathVariable(value="code") String code) {				
												
		menuCommandService.deleteWebResource(code);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	
}
