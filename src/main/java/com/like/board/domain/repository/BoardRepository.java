package com.like.board.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Board;
import com.querydsl.core.types.Predicate;

@Repository
public interface BoardRepository {
	
	/**
	 * 게시판 도메인을 조회
	 * @param id	게시판 도메인 PK
	 * @return	게시판 도메인
	 */
	Board getBoard(Long id);
	
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
		
	/**
	 * 게시판 도메인 저장
	 * @param board	게시판 도메인
	 */
	void saveBoard(Board board);
	
	/**
	 * 게시판 도메인 삭제
	 * @param id	게시판 도메인 PK
	 */
	void deleteBoard(Long id);
	
	/**
	 * 게시판 도메인 삭제
	 * @param board	게시판 도메인 
	 */
	void deleteBoard(Board board);
			
}