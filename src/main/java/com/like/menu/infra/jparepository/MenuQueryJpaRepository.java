package com.like.menu.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.menu.boundary.MenuDTO;
import com.like.menu.boundary.MenuDTO.SearchMenu;
import com.like.menu.boundary.MenuGroupDTO.SearchMenuGroup;
import com.like.menu.domain.model.Menu;
import com.like.menu.domain.model.MenuGroup;
import com.like.menu.domain.model.QMenu;
import com.like.menu.domain.model.QMenuGroup;
import com.like.menu.domain.model.QWebResource;
import com.like.menu.domain.repository.MenuQueryRepository;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuQueryJpaRepository implements MenuQueryRepository {

	private JPAQueryFactory queryFactory;
	private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;	
	private final QMenu qMenu = QMenu.menu;
	private final QWebResource qWebResource = QWebResource.webResource;
	
	public MenuQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuGroup> getMenuGroupList(SearchMenuGroup condition) {
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
	public List<Menu> getMenuList(SearchMenu condition) {
		return queryFactory
				.selectFrom(qMenu)
					.innerJoin(qMenu.menuGroup, qMenuGroup)
					.fetchJoin()
				.where(condition.getBooleanBuilder())				
				.fetch();
	}
	
	public List<MenuDTO.MenuHierarchy> getMenuRootList(String menuGroupCode) {
		
		Expression<Boolean> isLeaf = new CaseBuilder()											
											.when(qMenu.parent.menuCode.isNotNull()).then(true)
											.otherwise(false).as("isLeaf");
						
		JPAQuery<MenuDTO.MenuHierarchy> query = queryFactory
				.select(Projections.constructor(MenuDTO.MenuHierarchy.class
											, qMenu.menuGroup.menuGroupCode, qMenu.menuCode, qMenu.menuName
											, qMenu.parent.menuCode, qMenu.menuType, qMenu.sequence, qMenu.level, qWebResource.url ,isLeaf))
				.from(qMenu)
					.leftJoin(qMenu.resource, qWebResource)					
				.where(qMenu.menuGroup.menuGroupCode.eq(menuGroupCode)
					.and(qMenu.parent.menuCode.isNull()));													
				
		return query.fetch();
	}
			
	public List<MenuDTO.MenuHierarchy> getMenuChildrenList(String menuGroupCode, String parentMenuCode) {					
		
		Expression<Boolean> isLeaf = new CaseBuilder()										
											.when(qMenu.parent.menuCode.isNotNull()).then(true)
											.otherwise(false).as("isLeaf");
						
		JPAQuery<MenuDTO.MenuHierarchy> query = queryFactory
				.select(Projections.constructor(MenuDTO.MenuHierarchy.class
											, qMenu.menuGroup.menuGroupCode, qMenu.menuCode, qMenu.menuName
											, qMenu.parent.menuCode, qMenu.menuType, qMenu.sequence, qMenu.level, qWebResource.url, isLeaf))
				.from(qMenu)				
					.leftJoin(qMenu.resource, qWebResource)
				.where(qMenu.menuGroup.menuGroupCode.eq(menuGroupCode)
					.and(qMenu.parent.menuCode.eq(parentMenuCode)));
																		
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

}
