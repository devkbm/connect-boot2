package com.like.board.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.QArticle;
import com.like.board.domain.model.QArticleCheck;
import com.like.board.domain.model.QAttachedFile;
import com.like.board.domain.repository.ArticleQueryRepository;
import com.like.file.domain.model.QFileInfo;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ArticleQueryJpaRepository implements ArticleQueryRepository {

	private JPAQueryFactory queryFactory;
	
	private final QArticle qArticle = QArticle.article;
	private final QArticleCheck qArticleCheck = QArticleCheck.articleCheck;
	private final QFileInfo qFileInfo = QFileInfo.fileInfo;
	private final QAttachedFile qAttachedFile = QAttachedFile.attachedFile;
	
	public ArticleQueryJpaRepository(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}	
	
	public List<Article> getArticleList(Long fkBoard) { 			
		
		return queryFactory.select(qArticle)
						   .from(qArticle)	
						   .leftJoin(qArticle.files, qAttachedFile)
						   .fetchJoin()
						   .where(qArticle.board.pkBoard.eq(fkBoard))							
						   .fetch();				
	}
	
	@Override
	public List<Article> getArticleList(Predicate condition) {
		return queryFactory.select(qArticle).distinct()
				   .from(qArticle)		  				   
				   .leftJoin(qArticle.files, qAttachedFile)
				   .fetchJoin()		  				   
				   .leftJoin(qAttachedFile.fileInfo, qFileInfo)
				   .fetchJoin()
				   .where(condition)
				   .orderBy(qArticle.pkArticle.desc())
				   .fetch();
	}

	
}
