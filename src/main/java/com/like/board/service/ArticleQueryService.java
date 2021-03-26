package com.like.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.boundary.ArticleDTO;
import com.like.board.domain.model.Article;
import com.like.board.domain.repository.ArticleQueryRepository;
import com.like.board.infra.mapper.BoardMapper;

@Service
@Transactional(readOnly=true)
public class ArticleQueryService {

	private ArticleQueryRepository articleRepository;    
    private BoardMapper boardMapper;
    
    public ArticleQueryService(ArticleQueryRepository articleRepository
    						  ,BoardMapper boardMapper) {
    	this.articleRepository = articleRepository;
    	this.boardMapper = boardMapper;    	
    }
    
	public List<Article> getAritlceList(ArticleDTO.SearchArticle condition) {
		return articleRepository.getArticleList(condition.getBooleanBuilder());
	}
	
	public List<Map<String,Object>> getArticleList(ArticleDTO.SearchArticle condition) {
		return boardMapper.getArticleList(condition);
	}
    
}
