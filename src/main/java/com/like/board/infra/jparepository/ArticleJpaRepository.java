package com.like.board.infra.jparepository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Repository;

import com.like.board.domain.repository.ArticleRepository;
import com.like.board.infra.jparepository.springdata.JpaArticle;
import com.like.board.infra.jparepository.springdata.JpaArticleCheck;
import com.like.board.infra.jparepository.springdata.JpaAttachedFile;
import com.like.file.domain.model.FileInfo;
import com.like.file.domain.model.QFileInfo;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.like.board.domain.model.*;

@Repository
public class ArticleJpaRepository implements ArticleRepository {
	
	private final QArticle qArticle = QArticle.article;
	private final QArticleCheck qArticleCheck = QArticleCheck.articleCheck;
	private final QFileInfo qFileInfo = QFileInfo.fileInfo;
	private final QAttachedFile qAttachedFile = QAttachedFile.attachedFile;
	
	private JPAQueryFactory  queryFactory;	
		
	private JpaArticle jpaArticle;
		
	private JpaArticleCheck jpaArticleCheck;
		
	private JpaAttachedFile jpaAttachedFile;
	
	public ArticleJpaRepository(JPAQueryFactory queryFactory
							   ,JpaArticle jpaArticle
							   ,JpaArticleCheck jpaArticleCheck
							   ,JpaAttachedFile jpaAttachedFile) {
		this.queryFactory = queryFactory;
		this.jpaArticle = jpaArticle;
		this.jpaArticleCheck = jpaArticleCheck;
		this.jpaAttachedFile = jpaAttachedFile;
	}			

	public ArticleCheck findFkarticleAndSysuser(Long fkarticle, String userId) {
									
		return queryFactory.selectFrom(qArticleCheck)
					.where(qArticleCheck.createdBy.eq(userId)
					.and(qArticleCheck.article.pkArticle.eq(fkarticle)))
					.fetchOne();				
	}
		
	public Article getArticle(Long id) {					
		return jpaArticle.findById(id).orElse(null);		
	}
			
	public List<Article> getArticleList(Long fkBoard) { 			
				
		return queryFactory.select(qArticle)
						   .from(qArticle)	
						   .leftJoin(qArticle.files, qAttachedFile)
						   .fetchJoin()
						   .where(qArticle.board.pkBoard.eq(fkBoard))							
						   .fetch();				
	}
	
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
		
		//return (List<Article>) jpaArticle.findAll(queryDTO.getBooleanBuilder());		
	}	
	
	public Article saveArticle(Article article) {		
								
		setArticleSequence(article);			
																										
		return jpaArticle.saveAndFlush(article);
	}
	
	public void saveAttachedFile(AttachedFile attachedFile) {
		jpaAttachedFile.save(attachedFile);
	}
	
	public void saveAttachedFile(List<AttachedFile> attachedFileList) {
		jpaAttachedFile.saveAll(attachedFileList);
	}	
	
	public void deleteArticle(Article article) {		
		
		jpaArticle.delete(article);
	}
	
	public void deleteArticle(Long id) {				
		jpaArticle.deleteById(id);
	}
	
	public void deleteArticle(List<Article> articleList) {
		jpaArticle.deleteAll(articleList);
	}	
	
	public ArticleCheck getArticleCheck(Long fkarticle, String userId) {				
					
		return queryFactory
				.selectFrom(qArticleCheck)
				.where(qArticleCheck.createdBy.eq(userId)
				  .and(qArticleCheck.article.pkArticle.eq(fkarticle)))
				.fetchOne();			
	}
	
	public Integer getArticleNextSeq(Long pkboard) {
		
		Expression<Integer> seq = new CaseBuilder()				
										.when(qArticle.seq.max().isNull()).then(0)
										.otherwise(qArticle.seq.max()).as("seq");
		
		Integer rtn = queryFactory
						.select(seq)
			  			.from(qArticle)
			  			.where(qArticle.board.pkBoard.eq(pkboard))				  
			  			.fetchOne();					
		
		return rtn + 1;		
	}
	
	public Article updateArticleHitCnt(Long pkAriticle, String userId) {										
				
		Article article = jpaArticle.findById(pkAriticle).orElse(null);
		
		if (article == null) 
			throw new EntityNotFoundException(pkAriticle + " 존재하지 않습니다.");
		
		article.updateHitCnt();
		
		jpaArticle.save(article);
				
		ArticleCheck check = this.getArticleCheck(pkAriticle, userId);
		
		if (check == null) {
			check = new ArticleCheck(article);				
		}				
		
		check.updateHitCnt();
				
		jpaArticleCheck.save(check);
			 		
		return article;		
	}
	
	public List<FileInfo> getFileInfoList(Long pkArticle) {
				
		Optional<Article> entity = jpaArticle.findById(pkArticle);
		
		return entity.isPresent() ? entity.get().getAttachedFileInfoList() : null;
	}
	
	
	/**
	 * 게시글 엔티티의 순번을 설정한다.
	 * @param article
	 */
	private void setArticleSequence(Article article) {		
		
		if (article.getSeq() == null ) {							
			article.setSeq(getArticleNextSeq(article.getBoard().getPkBoard()));
		} else if (article.getSeq() == 0 ) {
			article.setSeq(1);
		}
	}	
	
}