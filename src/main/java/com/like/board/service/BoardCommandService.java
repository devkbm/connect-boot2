package com.like.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.board.boundary.ArticleDTO;
import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Article;
import com.like.board.domain.model.AttachedFile;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.BoardBookmark;
import com.like.board.domain.repository.ArticleRepository;
import com.like.board.domain.repository.BoardBookmarkRepository;
import com.like.board.domain.repository.BoardRepository;
import com.like.board.domain.service.AttachedFileConverter;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

@Service
@Transactional
public class BoardCommandService {
	
	private BoardRepository boardRepository;
	
	private BoardBookmarkRepository boardBookmarkRepository; 
    
	private ArticleRepository articleRepository;          
		    
    private FileService fileService;
    
	public BoardCommandService(BoardRepository boardRepository
							  ,BoardBookmarkRepository boardBookmarkRepository
							  ,ArticleRepository articleRepository
							  ,FileService fileService) {
		this.boardRepository = boardRepository;
		this.boardBookmarkRepository = boardBookmarkRepository;
		this.articleRepository = articleRepository;
		this.fileService = fileService;
	}

	public void saveBoard(BoardDTO.SaveBoard dto) {			
		Board board = null;			
		Board parentBoard = dto.getPpkBoard() != null ? boardRepository.getBoard(dto.getPpkBoard()) : null;			
																
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
	
	public List<BoardBookmark> getBookmarkList(String userId) {
		return boardBookmarkRepository.getBookmarkList(userId);
	}	
	
	public void saveBookmark(BoardBookmark entity) {
		boardBookmarkRepository.saveBookmark(entity);
	}
	
	public void deleteBookmark(Long id) {
		BoardBookmark entity = boardBookmarkRepository.getBookmark(id);
		
		boardBookmarkRepository.deleteBookmark(entity);
	}
	
	public Article convertEntity(ArticleDTO.SaveArticleByMuiltiPart dto) {		
		Board board = boardRepository.getBoard(dto.getFkBoard());		
		Article article = null; 
		
		if ( dto.getPkArticle() != null ) {
			article = articleRepository.getArticle(Long.parseLong(dto.getPkArticle()));
		}
		
		if (article == null) {
			article = dto.newArticle(board); 
		} else {
			dto.modifyArticle(article);			
		}
		
		return article;
	}
		
	public String saveArticle(ArticleDTO.SaveArticleByMuiltiPart dto) throws Exception {
		
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
	
	public String saveArticle(ArticleDTO.SaveArticleByJson dto) {		 							
		Board board = boardRepository.getBoard(dto.getFkBoard());
		Article article = null;
		List<FileInfo> fileInfoList = null;
		List<AttachedFile> attachedFileList = null;
		
		// 1. 기존 게시글이 있는지 조회한다. 
		if (dto.getPkArticle() != null) {
			article = articleRepository.getArticle(dto.getPkArticle());
		}
		
		// 2. 저장된 파일 리스트를 조회한다.
		if (dto.getAttachFile() != null) {
			fileInfoList = fileService.getFileInfoList(dto.getAttachFile());			
		}
		
		// 3. 게시글 객체를 생성한다.
		if (article == null) {
			article = dto.newArticle(board);
		} else {
			dto.modifyArticle(article);
		}		
		
		// 4. FileInfo를 AttachedFile로 변환한다.
		attachedFileList = AttachedFileConverter.convert(article, fileInfoList);
		
		if (attachedFileList != null) {
			article.setFiles(attachedFileList);
		}
				
		// 5. 게시글 저장 후 id 리턴
		return articleRepository.saveArticle(article).getId().toString();
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