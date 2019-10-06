package com.like.board.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.like.board.boundary.ArticleDTO;
import com.like.board.domain.model.Article;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {	
		
	private BoardCommandService boardCommandService;
		
	private BoardQueryService boardQueryService;		
		
	public ArticleController(BoardCommandService boardCommandService
							,BoardQueryService boardQueryService) {
		this.boardCommandService = boardCommandService;
		this.boardQueryService = boardQueryService;		
	}

	@GetMapping("/grw/board/article/{id}")
	public ResponseEntity<?> getArticle(@PathVariable(value="id") Long id, HttpSession session) {						
		
		Article article = boardQueryService.getArticle(id);		
	
		ArticleDTO.ArticleResponse response = ArticleDTO.ArticleResponse.converDTO(article);		
		
		// log.info(SecurityContextHolder.getContext().getAuthentication().getName());
		
		return WebControllerUtil.getResponse(response
											,article == null ? 0 : 1
											,article == null ? false : true
											,String.format("%d 건 조회되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@DeleteMapping("/grw/board/article/{id}")
	public ResponseEntity<?> deleteArticle(@PathVariable(value="id") Long id) {				
		
		boardCommandService.deleteArticle(id);							
		
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@GetMapping("/grw/board/article")
	public ResponseEntity<?> getArticleList(ArticleDTO.SearchArticle condition) {
																	
		List<Article> list = boardQueryService.getAritlceList(condition);  							
				
		return WebControllerUtil.getResponse(list
											,list.size()
											,true
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
				
		
	@DeleteMapping(value={"/grw/board/article"})
	public ResponseEntity<?> deleteArticle(@RequestBody List<Article> articleList) {						
		
		boardCommandService.deleteArticle(articleList);									
		
		return WebControllerUtil.getResponse(null
											,articleList.size()
											,true
											,String.format("%d 건 삭제되었습니다.", articleList.size())
											,HttpStatus.OK);
	}	
	
	@RequestMapping(value={"/grw/board/articletemp"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleWithMultiPartFile(ArticleDTO.SaveArticleByMuiltiPart dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors() ) {
			throw new ControllerException(result.getAllErrors().toString());
		}			
		log.info(dto.toString());														
		boardCommandService.saveArticle(dto);											
		
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/grw/board/article"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleJson(@RequestBody ArticleDTO.SaveArticleByJson dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors() ) {
			throw new ControllerException(result.getAllErrors().toString());
		}						
										
		boardCommandService.saveArticle(dto);											
		
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	
	@RequestMapping(value={"/grw/board/article/hitcnt"}, method=RequestMethod.GET) 
	public ResponseEntity<?> updateArticleHitCnt(@RequestParam(value="id", required=true) Long id,
												 @RequestParam(value="userid", required=true) String userId) {								
				
		Article aritlce = boardCommandService.updateArticleHitCnt(id, userId);			
										
		return WebControllerUtil.getResponse(aritlce
											,1
											,true
											,String.format("%d건 업데이트 하였습니다.", 1)
											,HttpStatus.OK);
	}	
	
}
