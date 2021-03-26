package com.like.board.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.board.boundary.BoardDTO;
import com.like.board.boundary.BoardDTO.BoardHierarchy;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.QBoard;
import com.like.board.domain.repository.BoardQueryRepository;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BoardQueryJpaRepository implements BoardQueryRepository {

	private JPAQueryFactory queryFactory;
	private final QBoard qBoard = QBoard.board;
	
	public BoardQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<Board> getBoardList(Predicate condition) {
		return queryFactory
				.selectFrom(qBoard)
				.where(condition)
				.fetch(); 	
	}

	@Override
	public List<BoardHierarchy> getBoardHierarchy() {
		List<BoardDTO.BoardHierarchy> rootList = getBoardHierarchyRootList();
		
		List<BoardDTO.BoardHierarchy> rtn =  setLinkBoardHierarchy(rootList);
		
		return rtn;
	}
	
	private List<BoardDTO.BoardHierarchy> setLinkBoardHierarchy(List<BoardDTO.BoardHierarchy> list) {
		List<BoardDTO.BoardHierarchy> children = null;
		
		for ( BoardDTO.BoardHierarchy dto : list) {
			
			children = getBoardHierarchyChildrenList(dto.getKey());
			
			if (children.isEmpty()) {	// leaf 노드이면 다음 리스트 검색
				dto.setLeaf(true);
				continue;
			} else {
				
				dto.setChildren(children);
				dto.setLeaf(false);
				
				setLinkBoardHierarchy(children);
			}
		}
		
		return list;
	}
	
	private List<BoardDTO.BoardHierarchy> getBoardHierarchyRootList() {									
		
		/*Expression<Boolean> isLeaf = new CaseBuilder()
										.when(qBoard.ppkBoard.isNotNull()).then(true)
										.otherwise(false).as("leaf");*/
		
		JPAQuery<BoardDTO.BoardHierarchy> query = queryFactory
				.select(Projections.constructor(BoardDTO.BoardHierarchy.class
						, qBoard.pkBoard, qBoard.parent.pkBoard, qBoard.boardType
						, qBoard.boardName, qBoard.boardDescription, qBoard.period.fromDate
						, qBoard.period.toDate, qBoard.articleCount, qBoard.sequence))
				.from(qBoard)
				.where(qBoard.isRootNode());
													
						
		return query.fetch();	
	}
	
	private List<BoardDTO.BoardHierarchy> getBoardHierarchyChildrenList(Long parentPkBoard) {
		
		JPAQuery<BoardDTO.BoardHierarchy> query = queryFactory
				.select(Projections.constructor(BoardDTO.BoardHierarchy.class
						, qBoard.pkBoard, qBoard.parent.pkBoard, qBoard.boardType
						, qBoard.boardName, qBoard.boardDescription, qBoard.period.fromDate
						, qBoard.period.toDate, qBoard.articleCount, qBoard.sequence))
				.from(qBoard)
				.where(qBoard.parent.pkBoard.eq(parentPkBoard));								
		
		return query.fetch();
		
	}

}
