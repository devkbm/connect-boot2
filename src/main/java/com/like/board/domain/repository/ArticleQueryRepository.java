package com.like.board.domain.repository;

import java.util.List;

import com.like.board.domain.model.Article;
import com.querydsl.core.types.Predicate;

public interface ArticleQueryRepository {

	/**
	 * 게시글 엔티티 리스트 조회
	 * @param fkBoard	게시글 엔티티 FK
	 * @return 게시글 도메인 리스트
	 */
	List<Article> getArticleList(Long fkBoard);
		
	/**
	 * 게시글 엔티티 리스트 조회
	 * @param fkBoard		게시글 엔티티 FK
	 * @param likeTitle		제목
	 * @param likeContents	내용
	 * @return	게시글 엔티티 리스트
	 */
	List<Article> getArticleList(Predicate condition);
		
}
