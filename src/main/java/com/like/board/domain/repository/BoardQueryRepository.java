package com.like.board.domain.repository;

import java.util.List;

import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Board;
import com.querydsl.core.types.Predicate;

public interface BoardQueryRepository {

	/**
	 * 전체 게시판 도메인 리스트를 조회
	 * @return	게시판 도메인 리스트
	 */
	List<Board> getBoardList(Predicate condition);
			
	/**
	 * 게시판 계층명단을 조회
	 * @return	
	 */
	List<BoardDTO.BoardHierarchy> getBoardHierarchy();
}
