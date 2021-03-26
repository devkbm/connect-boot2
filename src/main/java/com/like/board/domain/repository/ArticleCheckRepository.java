package com.like.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.ArticleCheck;

@Repository
public interface ArticleCheckRepository extends JpaRepository<ArticleCheck, Long> {

	ArticleCheck findByCreatedByAndArticle(String userId, Article fkarticle);
	
}


/*
queryFactory
				.selectFrom(qArticleCheck)
				.where(qArticleCheck.createdBy.eq(userId)
				  .and(qArticleCheck.article.pkArticle.eq(fkarticle)))
				.fetchOne();			
	}
*/