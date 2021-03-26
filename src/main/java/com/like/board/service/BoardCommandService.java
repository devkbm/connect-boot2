package com.like.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.BoardBookmark;
import com.like.board.domain.repository.BoardBookmarkRepository;
import com.like.board.domain.repository.BoardRepository;


@Service
@Transactional
public class BoardCommandService {
	
	private BoardRepository boardRepository;	
	private BoardBookmarkRepository boardBookmarkRepository; 
        
	public BoardCommandService(BoardRepository boardRepository
							  ,BoardBookmarkRepository boardBookmarkRepository) {
		this.boardRepository = boardRepository;
		this.boardBookmarkRepository = boardBookmarkRepository;		
	}

	public Board getBoard(Long id) {
    	return boardRepository.findById(id).orElse(null);
    }
	
	public void saveBoard(BoardDTO.SaveBoard dto) {			
		Board board = null;			
		Board parentBoard = dto.getPpkBoard() != null ? boardRepository.findById(dto.getPpkBoard()).orElse(null) : null;			
																
		if (dto.getPkBoard() == null) {
			board = dto.newBoard(parentBoard);
		} else {
			board = boardRepository.findById(dto.getPkBoard()).orElse(null);
			dto.modifyBoard(board, parentBoard);			
		}			
		
		boardRepository.save(board);
	}
	
	public void deleteBoard(Long id) {
		boardRepository.deleteById(id);
	}			
	
	public void deleteBoard(Board board) {
		boardRepository.delete(board);
	}
	
	public List<BoardBookmark> getBookmarkList(String userId) {
		return boardBookmarkRepository.getBookmarkList(userId);
	}	
	
	public void saveBookmark(BoardBookmark entity) {
		boardBookmarkRepository.saveBookmark(entity);
	}
	
	public void deleteBookmark(Long id) {
		BoardBookmark entity = boardBookmarkRepository.getBookmark(id);
		
		boardBookmarkRepository.deleteBookmark(entity);
	}							
		
}