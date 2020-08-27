package com.like.board.domain.repository;

import java.util.List;

import com.like.board.domain.model.BoardBookmark;

public interface BoardBookmarkRepository {

	List<BoardBookmark> getBookmarkList(String userId);
	
	BoardBookmark getBookmark(Long id);
	
	void saveBookmark(BoardBookmark entity);
	
	void deleteBookmark(BoardBookmark entity);
	
}
