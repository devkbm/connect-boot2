package com.like.menu;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.file.service.FileService;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.WebResource;
import com.like.menu.domain.model.enums.MenuType;
import com.like.menu.service.MenuCommandService;
import com.like.menu.service.MenuQueryService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuServiceTest {
		 
	@Autowired
	MenuCommandService menuCommandService;
	
	@Autowired
	MenuQueryService menuQueryService;	
	
	@Autowired
	FileService fs;
		
	@Before 
    public void setUp() throws Exception { 
		//테스트 데이터 입력
		MenuGroup menuGroup = new MenuGroup("GROUP","테스트메뉴그룹","테스트메뉴그룹");				
		menuCommandService.saveMenuGroup(menuGroup);			
		
		Menu menu = new Menu("MENU", "테스트메뉴", null, MenuType.ITEM, 0L, 0L, null, null);
		menuCommandService.saveMenu(menu, menuGroup.getMenuGroupCode());
					
		WebResource program = WebResource.builder()
											.resourceCode("Program")
											.resourceName("테스트프로그램")
											.resourceCode("TYPE")
											.url("/home")
											.description("테스트프로그램입니다.")
											.build();
																							
		menuCommandService.saveWebResource(program);		
    } 
	
	@Test	
	public void test01_메뉴그룹등록및조회() {
		//Given
		MenuGroup menuGroup = new MenuGroup("Test","테스트메뉴그룹","테스트메뉴그룹");		
		
		//When
		menuCommandService.saveMenuGroup(menuGroup);
		
		//Then
		MenuGroup test = menuQueryService.getMenuGroup(menuGroup.getMenuGroupCode());
		
		assertThat(test.getMenuGroupCode()).isEqualTo("Test");
		assertThat(test.getMenuGroupName()).isEqualTo("테스트메뉴그룹");
		assertThat(test.getDescription()).isEqualTo("테스트메뉴그룹");			
	}
		
	
	@Test
	public void test02_메뉴등록및조회() throws Exception {
		//Given
		MenuGroup menuGroup = new MenuGroup("GROUP","테스트메뉴그룹","테스트메뉴그룹");				
		menuCommandService.saveMenuGroup(menuGroup);
		
		//When		
		Menu menu = Menu.builder()
						.menuCode("testmenu")
						.menuName("테스트메뉴")
						.menuType(MenuType.ITEM)
						.level(0L)
						.sequence(0L)						
						.build();
		
		menuCommandService.saveMenu(menu, menuGroup.getMenuGroupCode());		
		
		//Then
		Menu test = menuQueryService.getMenu(menu.getMenuCode());
								
		assertThat(test.getMenuCode()).isEqualTo("testmenu");
		assertThat(test.getMenuName()).isEqualTo("테스트메뉴");
		assertThat(test.getMenuType()).isEqualTo(MenuType.ITEM);
		assertThat(test.getSequence()).isEqualTo(0L);
		assertThat(test.getLevel()).isEqualTo(0L);
				
	}
	
	@Test	
	public void test03_프로그램등록및조회() throws Exception {	
		//Given
		WebResource program = WebResource.builder()
								.resourceCode("Program")
								.resourceName("테스트프로그램")
								.resourceType("TYPE")
								.url("/home")
								.description("테스트프로그램입니다.")
								.build();			
		
		//When
		menuCommandService.saveWebResource(program);
		
		//Then
		WebResource test = menuQueryService.getResource(program.getResourceCode());
		
		assertThat(test.getResourceCode()).isEqualTo("Program");
		assertThat(test.getResourceName()).isEqualTo("테스트프로그램");
		assertThat(test.getResourceType()).isEqualTo("TYPE");
		assertThat(test.getUrl()).isEqualTo("/home");
		assertThat(test.getDescription()).isEqualTo("테스트프로그램입니다.");		
	}
	
}
