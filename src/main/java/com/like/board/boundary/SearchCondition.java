package com.like.board.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.board.domain.model.QArticle;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Data;

public class SearchCondition {
	
	@Data
	public static class ArticleSearch implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QArticle qArticle = QArticle.article;
		
		Long fkBoard;
		
		String title;
		
		String contents;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(qArticle.board.pkBoard.eq(fkBoard))
				.and(likeTitle(this.title))
				.and(likeContents(this.contents));											
			
			return builder;
		}
		
		private BooleanExpression likeTitle(String title) {
			if (StringUtils.isEmpty(title)) {
				return null;
			}
			
			return qArticle.title.like("%"+title+"%");
		}
		
		private BooleanExpression likeContents(String contents) {
			if (StringUtils.isEmpty(contents)) {
				return null;
			}
			
			return qArticle.contents.like("%"+contents+"%");
		}
		
		
	}
	
}
