package com.like.board.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.like.board.domain.model.Article;
import com.like.board.domain.model.AttachedFile;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.BoardDTOAssembler;
import com.like.board.domain.model.enums.BoardType;
import com.like.board.domain.service.AttachedFileConverter;
import com.like.board.dto.ArticleDTO;
import com.like.board.dto.ArticleDTO.ArticleResponse;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.file.domain.model.FileInfo;
import com.like.file.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {	
	
	@Resource
	private BoardCommandService boardCommandService;
	
	@Resource
	private BoardQueryService boardQueryService;
	
	@Resource
	private FileService fileService;	
		
	@GetMapping("/grw/board/article/{id}")
	public ResponseEntity<?> getArticle(@PathVariable(value="id") Long id) {						
		
		Article article = boardQueryService.getArticle(id);		
	
		ArticleDTO.ArticleResponse response = BoardDTOAssembler.converDTO(article);
		
		return WebControllerUtil.getResponse(response, 
				article == null ? 0 : 1, 
				article == null ? false : true, 
				String.format("%d 건 조회되었습니다.", 1), 
				HttpStatus.OK);
	}
		
	@DeleteMapping("/grw/board/article/{id}")
	public ResponseEntity<?> deleteArticle(@PathVariable(value="id") Long id) {				
		
		boardCommandService.deleteArticle(id);							
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 삭제되었습니다.", 1), 
				HttpStatus.OK);
	}
		
	@GetMapping("/grw/board/article")
	public ResponseEntity<?> getArticleList(ArticleDTO.QueryCondition condition) {
																	
		List<Article> list = boardQueryService.getAritlceList(condition);  							
				
		return WebControllerUtil.getResponse(list, 
				list.size(), 
				true, 
				String.format("%d 건 조회되었습니다.", list.size()), 
				HttpStatus.OK);
	}
				
		
	@DeleteMapping(value={"/grw/board/article"})
	public ResponseEntity<?> deleteArticle(@RequestBody List<Article> articleList) {						
		
		boardCommandService.deleteArticle(articleList);									
		
		return WebControllerUtil.getResponse(null, 
				articleList.size(), 
				true, 
				String.format("%d 건 삭제되었습니다.", articleList.size()), 
				HttpStatus.OK);
	}	
	
	@RequestMapping(value={"/grw/board/articletemp"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleWithMultiPartFile(ArticleDTO.ArticleSaveMuiltiPart dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors() ) {
			throw new ControllerException(result.getAllErrors().toString());
		}			
		log.info(dto.toString());														
		boardCommandService.saveArticle(dto);											
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}
	
	@RequestMapping(value={"/grw/board/article"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleJson(@RequestBody ArticleDTO.ArticleSaveJson dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors() ) {
			throw new ControllerException(result.getAllErrors().toString());
		}						
				
		Article article = this.convertEntity(dto);						
		
		boardCommandService.saveArticle(article);											
		
		return WebControllerUtil.getResponse(null, 
				1, 
				true, 
				String.format("%d 건 저장되었습니다.", 1), 
				HttpStatus.OK);
	}
		
	
	@RequestMapping(value={"/grw/board/article/hitcnt"}, method=RequestMethod.GET) 
	public ResponseEntity<?> updateArticleHitCnt(@RequestParam(value="id", required=true) Long id,
			@RequestParam(value="userid", required=true) String userId) {								
				
		Article aritlce = boardCommandService.updateArticleHitCnt(id, userId);			
										
		return WebControllerUtil.getResponse(aritlce, 
				1, 
				true, 
				String.format("%d건 업데이트 하였습니다.", 1), 
				HttpStatus.OK);
	}
	
	
	public Article convertEntity(ArticleDTO.ArticleSaveMuiltiPart dto) {
		
		Board board = boardQueryService.getBoard(dto.getFkBoard());		
		Article article = boardQueryService.getArticle(Long.parseLong(dto.getPkArticle()));
		
		if (article == null) {
			article = BoardDTOAssembler.createEntity(dto, board);
		} else {
			article = BoardDTOAssembler.mergeEntity(article, dto);
		}
		
		return article;
	}
	
	
	private Article convertEntity(ArticleDTO.ArticleSaveJson dto) {
		Board board = boardQueryService.getBoard(dto.getFkBoard());
		Article article = null;
		List<FileInfo> fileInfoList = null;
		List<AttachedFile> attachedFileList = null;
		
		// 1. 기존 게시글이 있는지 조회한다. 
		if (dto.getPkArticle() != null) {
			article = boardQueryService.getArticle(dto.getPkArticle());
		}
		
		// 2. 저장된 파일 리스트를 조회한다.
		if (dto.getAttachFile() != null) {
			fileInfoList = fileService.getFileInfoList(dto.getAttachFile());
			
		}
		
		// 3. 게시글 객체를 생성한다.
		if (article == null) {
			article =  Article.builder()	
								.board(board)
								.ppkArticle(dto.getPpkArticle())
								.title(dto.getTitle())
								.contents(dto.getContents())
								.fromDate(dto.getFromDate())
								.toDate(dto.getToDate())
								.pwd(dto.getPwd())		
								//.files(attachedFileList)
								.build();
		} else {
			article =  Article.builder()	
								.board(board)
								.pkArticle(dto.getPkArticle())
								.ppkArticle(dto.getPpkArticle())
								.title(dto.getTitle())
								.contents(dto.getContents())
								.fromDate(dto.getFromDate())
								.toDate(dto.getToDate())
								.pwd(dto.getPwd())
								//.files(attachedFileList)
								.build();
		}		
		
		// 4. FileInfo를 AttachedFile로 변환한다.
		attachedFileList = AttachedFileConverter.convert(article, fileInfoList);
		
		if (attachedFileList != null) {
			article.setFiles(attachedFileList);
		}
		
		return article;
	}
}
