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
import com.like.board.service.ArticleCommandService;
import com.like.core.web.exception.ControllerException;
import com.like.core.web.util.WebControllerUtil;

@Controller
public class ArticleController {	
		
	private ArticleCommandService service;			
		
	public ArticleController(ArticleCommandService service) {
		this.service = service;		
	}	
	
	@GetMapping("/grw/board/article/{id}")
	public ResponseEntity<?> getArticle(@PathVariable(value="id") Long id, HttpSession session) {						
		
		Article article = service.getArticle(id);		
	
		ArticleDTO.ArticleResponse response = ArticleDTO.ArticleResponse.converDTO(article);				
		
		return WebControllerUtil.getResponse(response											
											,String.format("%d 건 조회되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	@DeleteMapping("/grw/board/article/{id}")
	public ResponseEntity<?> deleteArticle(@PathVariable(value="id") Long id) {				
		
		service.deleteArticle(id);							
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}
			
	@DeleteMapping(value={"/grw/board/article"})
	public ResponseEntity<?> deleteArticle(@RequestBody List<Article> articleList) {						
		
		service.deleteArticle(articleList);									
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 삭제되었습니다.", articleList.size())
											,HttpStatus.OK);
	}	
	
	@RequestMapping(value={"/grw/board/articletemp"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleWithMultiPartFile(ArticleDTO.SaveArticleByMuiltiPart dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors() ) {
			throw new ControllerException(result.getAllErrors().toString());
		}			
											
		service.saveArticle(dto);											
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/grw/board/article"}, method={RequestMethod.POST,RequestMethod.PUT})
	@ResponseBody
	public ResponseEntity<?> saveArticleJson(@RequestBody ArticleDTO.SaveArticleByJson dto, BindingResult result) throws Exception {
											
		if ( result.hasErrors() ) {
			throw new ControllerException(result.getAllErrors().toString());
		}						
										
		service.saveArticle(dto);											
		
		return WebControllerUtil.getResponse(null											
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}
		
	
	@RequestMapping(value={"/grw/board/article/hitcnt"}, method=RequestMethod.GET) 
	public ResponseEntity<?> updateArticleHitCnt(@RequestParam(value="id", required=true) Long id,
												 @RequestParam(value="userid", required=true) String userId) {								
				
		Article aritlce = service.updateArticleHitCnt(id, userId);			
										
		return WebControllerUtil.getResponse(aritlce											
											,String.format("%d건 업데이트 하였습니다.", 1)
											,HttpStatus.OK);
	}	
	
}
