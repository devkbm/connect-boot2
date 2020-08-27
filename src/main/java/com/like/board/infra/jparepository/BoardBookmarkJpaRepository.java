package com.like.board.infra.jparepository;

import java.util.List;

import org.springframework.data.querydsl.QSort;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.like.board.domain.model.BoardBookmark;
import com.like.board.domain.model.QBoardBookmark;
import com.like.board.domain.repository.BoardBookmarkRepository;
import com.like.board.infra.jparepository.springdata.JpaBoardBookmark;

@Repository
public class BoardBookmarkJpaRepository implements BoardBookmarkRepository {

	private JpaBoardBookmark jpaBoardBookmark;
	
	public BoardBookmarkJpaRepository(JpaBoardBookmark jpaBoardBookmark) {
		this.jpaBoardBookmark = jpaBoardBookmark;
	}
	
	@Override
	public List<BoardBookmark> getBookmarkList(String userId) {
		QBoardBookmark q = QBoardBookmark.boardBookmark;
		
		return Lists.newArrayList(jpaBoardBookmark.findAll(q.userId.eq(userId), new QSort(q.seq.asc())));
	}

	@Override
	public BoardBookmark getBookmark(Long id) {
		return jpaBoardBookmark.findById(id).orElse(null);
	}

	@Override
	public void saveBookmark(BoardBookmark entity) {
		jpaBoardBookmark.save(entity);		
	}

	@Override
	public void deleteBookmark(BoardBookmark entity) {
		jpaBoardBookmark.delete(entity);		
	}

}
