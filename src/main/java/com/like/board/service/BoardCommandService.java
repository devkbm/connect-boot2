package com.like.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.boundary.ArticleDTO;
import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Article;
import com.like.board.domain.model.AttachedFile;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.BoardDTOAssembler;
import com.like.board.domain.repository.ArticleRepository;
import com.like.board.domain.repository.BoardRepository;
import com.like.board.domain.service.AttachedFileConverter;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BoardCommandService {
	
    @Resource(name="boardJpaRepository")
	private BoardRepository boardRepository;
    
    @Resource(name="articleJpaRepository")
	private ArticleRepository articleRepository;          
		
    @Resource(name="fileService")
    private FileService fileService;
    	
	public void saveBoard(BoardDTO.SaveBoard dto) {			
		Board board = null;			
		Board parentBoard = null;
		
		if (dto.getPpkBoard() != null) {
			parentBoard = boardRepository.getBoard(dto.getPpkBoard());
		}
																
		if (dto.getPkBoard() == null) {
			board = dto.newBoard(parentBoard);
		} else {
			board = boardRepository.getBoard(dto.getPkBoard());
			dto.modifyBoard(board, parentBoard);			
		}			
		
		boardRepository.saveBoard(board);
	}
	
	public void deleteBoard(Long id) {
		boardRepository.deleteBoard(id);
	}			
	
	public void deleteBoard(Board board) {
		boardRepository.deleteBoard(board);
	}
	
	public Article convertEntity(ArticleDTO.ArticleSaveMuiltiPart dto) {
		log.info(dto.toString());
		Board board = boardRepository.getBoard(dto.getFkBoard());		
		Article article = null; 
		
		if ( dto.getPkArticle() != null ) {
			article = articleRepository.getArticle(Long.parseLong(dto.getPkArticle()));
		}
		
		if (article == null) {
			article = BoardDTOAssembler.createEntity(dto, board);
		} else {
			article = BoardDTOAssembler.mergeEntity(article, dto);
		}
		
		return article;
	}
		
	public String saveArticle(ArticleDTO.ArticleSaveMuiltiPart dto) throws Exception {
		
		List<FileInfo> fileInfoList = null;
		List<AttachedFile> attachedFileList = null;					
		
		Article article = convertEntity(dto);			
		
		// 첨부파일 저장
		if (!dto.getFile().isEmpty()) {		
			fileInfoList = fileService.uploadFile(dto.getFile(), "test", "board");
			attachedFileList = AttachedFileConverter.convert(article, fileInfoList);
		}
		
		article.setFiles(attachedFileList);												
									 											
		return this.saveArticle(article);
	}	
	
	public String saveArticle(Article article) {
		String pkArticle = articleRepository.saveArticle(article).getId().toString(); 						
		
		return pkArticle;
	}

	public void deleteArticle(Article article) {					
		articleRepository.deleteArticle(article);
	}
	
	public void deleteArticle(Long id) {					
		articleRepository.deleteArticle(id);
	}
	
	public void deleteArticle(List<Article> articleList) {					
		articleRepository.deleteArticle(articleList);
	}
	
	public Article updateArticleHitCnt(Long pkAriticle, String userId) {		
		return articleRepository.updateArticleHitCnt(pkAriticle, userId);
	}			
	
		
}