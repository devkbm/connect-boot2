package com.like.board.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.boundary.ArticleDTO;
import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.domain.repository.ArticleRepository;
import com.like.board.domain.repository.BoardRepository;
import com.like.board.infra.mapper.BoardMapper;

@Service
@Transactional(readOnly=true)
public class BoardQueryService {

	@Resource(name="boardJpaRepository")
	private BoardRepository boardRepository;
    
    @Resource(name="articleJpaRepository")
	private ArticleRepository articleRepository;
    
    @Resource(name="boardMapper")
    private BoardMapper boardMapper;
    
    public Board getBoard(Long id) {
    	return boardRepository.getBoard(id);
    }
    
	public List<Board> getBoardList(BoardDTO.SearchBoard condition) {
		return boardRepository.getBoardList(condition.getBooleanBuilder());
	}	
		
	public List<?> getBoardHierarchy() {
		return boardRepository.getBoardHierarchy();
	}
	
	public Article getArticle(Long id) {
		return articleRepository.getArticle(id);		
	}
			
	public List<Article> getAritlceList(ArticleDTO.SearchArticle condition) {
		return articleRepository.getArticleList(condition.getBooleanBuilder());
	}
	
	public List<Map<String,Object>> getArticleList(ArticleDTO.SearchArticle condition) {
		return boardMapper.getArticleList(condition);
	}
}
