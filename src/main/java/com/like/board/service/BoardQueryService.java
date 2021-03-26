package com.like.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Board;
import com.like.board.domain.repository.BoardQueryRepository;


@Service
@Transactional(readOnly=true)
public class BoardQueryService {
	
	private BoardQueryRepository boardRepository;        	
    
    public BoardQueryService(BoardQueryRepository boardRepository) {
    	this.boardRepository = boardRepository;    	
    }        
    
	public List<Board> getBoardList(BoardDTO.SearchBoard condition) {
		return boardRepository.getBoardList(condition.getBooleanBuilder());
	}	
		
	public List<?> getBoardHierarchy() {
		return boardRepository.getBoardHierarchy();
	}		
			
}
