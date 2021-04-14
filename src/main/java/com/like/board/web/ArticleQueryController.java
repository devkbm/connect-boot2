package com.like.board.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.like.board.boundary.ArticleDTO;
import com.like.board.domain.model.Article;
import com.like.board.service.ArticleQueryService;
import com.like.core.web.util.WebControllerUtil;

@Controller
public class ArticleQueryController {

	private ArticleQueryService service;
	
	public ArticleQueryController(ArticleQueryService service) {
		this.service = service;		
	}
	
	@GetMapping("/grw/board/article")
	public ResponseEntity<?> getArticleList(ArticleDTO.SearchArticle condition) {
																	
		List<Article> list = service.getAritlceList(condition);  							
		
		List<ArticleDTO.ArticleResponse> dtoList = list.stream()
													   .map(e -> ArticleDTO.ArticleResponse.converDTO((e)))
													   .collect(Collectors.toList());		
		
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
}
