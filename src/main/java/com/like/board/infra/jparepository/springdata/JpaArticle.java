package com.like.board.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Article;

@Repository
public interface JpaArticle extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article> {
	
}
