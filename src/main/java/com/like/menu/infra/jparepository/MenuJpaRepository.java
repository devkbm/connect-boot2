package com.like.menu.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.WebResource;
import com.like.menu.domain.model.QMenu;
import com.like.menu.domain.model.QMenuGroup;
import com.like.menu.domain.model.QWebResource;
import com.like.menu.domain.repository.MenuRepository;
import com.like.menu.dto.MenuDTO;
import com.like.menu.dto.MenuGroupDTO;
import com.like.menu.dto.WebResourceDTO;
import com.like.menu.infra.jparepository.springdata.JpaMenu;
import com.like.menu.infra.jparepository.springdata.JpaMenuGroup;
import com.like.menu.infra.jparepository.springdata.JpaWebResource;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MenuJpaRepository implements MenuRepository {
				
	@Autowired
	private JPAQueryFactory	queryFactory;
	
	@Autowired
	private JpaMenuGroup jpaMenuGroup;
	
	@Autowired
	private JpaMenu jpaMenu;
	
	@Autowired
	private JpaWebResource jpaWebResource;
	
	private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;	
	private final QMenu qMenu = QMenu.menu;	
	private final QWebResource qWebResource = QWebResource.webResource;

	@Override
	public MenuGroup getMenuGroup(String menuGroupCode) {
		Optional<MenuGroup> entity = jpaMenuGroup.findById(menuGroupCode);
					
		return entity.isPresent() ? entity.get() : null;
	}

	@Override
	public List<MenuGroup> getMenuGroupList(MenuGroupDTO.QueryCondition condition) {
		return queryFactory
				.selectFrom(qMenuGroup)
				.where(condition.getBooleanBuilder())
				.fetch();
	}

	@Override
	public List<MenuGroup> getMenuGroupList(String likeMenuGroupName) {
		return queryFactory
				.selectFrom(qMenuGroup)
				.where(qMenuGroup.menuGroupName.like(likeMenuGroupName+"%"))
				.fetch();
	}

	@Override
	public List<MenuGroup> getMenuGroupList(List<String> menuGroupNameList) {
		return jpaMenuGroup.findAllById(menuGroupNameList);
	}

	@Override
	public void saveMenuGroup(MenuGroup codeGroup) {
		jpaMenuGroup.save(codeGroup);		
	}

	@Override
	public void deleteMenuGroup(String menuGroupCode) {
		jpaMenuGroup.deleteById(menuGroupCode);		
	}

	@Override
	public Menu getMenu(String menuCode) {
		Optional<Menu> entity = jpaMenu.findById(menuCode);
		return entity.isPresent() ? entity.get() : null;
	}

	@Override
	public List<Menu> getMenuList(MenuDTO.QueryCondition condition) {
		return queryFactory
				.selectFrom(qMenu)
					.innerJoin(qMenu.menuGroup, qMenuGroup)
					.fetchJoin()
				.where(condition.getBooleanBuilder())				
				.fetch();
	}
	
	
	public List<MenuDTO.MenuHierarchy> getMenuRootList(String menuGroupCode) {
								
		Expression<Boolean> isLeaf = new CaseBuilder()											
											.when(qMenu.parentMenuCode.isNotNull()).then(true)
											.otherwise(false).as("isLeaf");
						
		JPAQuery<MenuDTO.MenuHierarchy> query = queryFactory
				.select(Projections.constructor(MenuDTO.MenuHierarchy.class
											, qMenu.menuGroup.menuGroupCode, qMenu.menuCode, qMenu.menuName
											, qMenu.parentMenuCode, qMenu.menuType, qMenu.sequence, qMenu.level, qWebResource.url ,isLeaf))
				.from(qMenu)
					.leftJoin(qMenu.resource, qWebResource)					
				.where(qMenu.menuGroup.menuGroupCode.eq(menuGroupCode)
					.and(qMenu.parentMenuCode.isNull()));													
				
		return query.fetch();
	}
			
	public List<MenuDTO.MenuHierarchy> getMenuChildrenList(String menuGroupCode, String parentMenuCode) {					
		
		Expression<Boolean> isLeaf = new CaseBuilder()										
											.when(qMenu.parentMenuCode.isNotNull()).then(true)
											.otherwise(false).as("isLeaf");
						
		JPAQuery<MenuDTO.MenuHierarchy> query = queryFactory
				.select(Projections.constructor(MenuDTO.MenuHierarchy.class
											, qMenu.menuGroup.menuGroupCode, qMenu.menuCode, qMenu.menuName
											, qMenu.parentMenuCode, qMenu.menuType, qMenu.sequence, qMenu.level, qWebResource.url, isLeaf))
				.from(qMenu)				
					.leftJoin(qMenu.resource, qWebResource)
				.where(qMenu.menuGroup.menuGroupCode.eq(menuGroupCode)
					.and(qMenu.parentMenuCode.eq(parentMenuCode)));
																		
		return query.fetch();
	}
	

	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	public List<MenuDTO.MenuHierarchy> getMenuHierarchyDTO(List<MenuDTO.MenuHierarchy> list) {
		List<MenuDTO.MenuHierarchy> children = null;
		
		for ( MenuDTO.MenuHierarchy dto : list ) {			
			if (dto.isLeaf()) { // leaf 노드이면 다음 리스트 검색
				continue;
			} else {				
				children = getMenuChildrenList(dto.getMenuGroupCode(), dto.getKey());
				dto.setChildren(children);
				
				getMenuHierarchyDTO(children);
			}
		}
		
		return list;
	}

	@Override
	public void saveMenu(Menu menu, MenuGroup menuGroup) {
		menu.setMenuGroup(menuGroup);		
		jpaMenu.save(menu);		
	}

	@Override
	public void deleteMenu(String menuCode) {
		jpaMenu.deleteById(menuCode);
	}

	@Override
	public WebResource getResource(String resourceCode) {
		Optional<WebResource> entity = jpaWebResource.findById(resourceCode);
		 
		return entity.isPresent() ? entity.get() : null;
	}

	@Override
	public List<WebResource> getResourceList(WebResourceDTO.QueryCondition condition) {
					
		return queryFactory
				.selectFrom(qWebResource)
				.where(condition.getBooleanBuilder())
				.fetch();					
	}

	@Override
	public void saveResource(WebResource resource) {		
		jpaWebResource.save(resource);		
	}

	@Override
	public void deleteResource(String resourceCode) {
		jpaWebResource.deleteById(resourceCode);		
	}					
	
}