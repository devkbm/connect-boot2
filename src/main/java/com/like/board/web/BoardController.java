package com.like.board.web;

import java.util.ArrayList;
import java.util.List;


import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.BoardBookmark;
import com.like.board.domain.model.enums.BoardType;
import com.like.board.service.BoardCommandService;
import com.like.board.service.BoardQueryService;
import com.like.common.web.exception.ControllerException;
import com.like.common.web.util.WebControllerUtil;
import com.like.menu.boundary.EnumDTO;

import lombok.extern.slf4j.Slf4j;

/**
 * 게시판 Rest 컨트롤러
 * 
 * @author 	bmkim
 * @date	2016.2.23
 */
@Slf4j
@RestController
public class BoardController {
	
	private BoardCommandService boardCommandService;
		
	private BoardQueryService boardQueryService;	
		
	public BoardController(BoardCommandService boardCommandService
						  ,BoardQueryService boardQueryService) {
		this.boardCommandService = boardCommandService;
		this.boardQueryService = boardQueryService;
	}

	@GetMapping("/grw/board/boardType")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<EnumDTO> list = new ArrayList<EnumDTO>();
		
		for (BoardType boardType : BoardType.values()) {
			EnumDTO dto = new EnumDTO(boardType.toString(), boardType.getName());
			list.add(dto);
		}				 					
		
		return WebControllerUtil.getResponse(list
											,list.size()
											,true
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/grw/boardHierarchy")
	public ResponseEntity<?> getBoardHierarchyList() {
											
		List<?> list = boardQueryService.getBoardHierarchy();				 			
		
		return WebControllerUtil.getResponse(list
											,list.size()
											,true
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}

	@GetMapping("/grw/board")
	public ResponseEntity<?> getBoardList(BoardDTO.SearchBoard dto) {						
		
		List<Board> list = boardQueryService.getBoardList(dto); 										
							
		return WebControllerUtil.getResponse(list
											,list.size()
											,true
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
		
	@GetMapping("/grw/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable(value="id") Long id) {				
				
		Board board = boardQueryService.getBoard(id);		
		
		BoardDTO.SaveBoard dto = BoardDTO.SaveBoard.convertDTO(board);				
							
		return WebControllerUtil.getResponse(dto
											,board != null ? 1 : 0
											,true
											,String.format("%d 건 조회되었습니다.", board != null ? 1 : 0)
											,HttpStatus.OK);
	}	
		
	@RequestMapping(value={"/grw/board"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveBoard(@RequestBody @Valid final BoardDTO.SaveBoard boardDTO, BindingResult result) {
							
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		log.info(authentication.getPrincipal().toString());*/
		
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 			
							
		log.info(boardDTO.toString());
		
		boardCommandService.saveBoard(boardDTO);				
								 					
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 저장되었습니다.", 1)
											,HttpStatus.OK);
	}	
		
	@DeleteMapping("/grw/board/{id}")
	public ResponseEntity<?> delBoard(@PathVariable(value="id") Long id) {					
												
		boardCommandService.deleteBoard(id);							
		
		return WebControllerUtil.getResponse(null
											,1
											,true
											,String.format("%d 건 삭제되었습니다.", 1)
											,HttpStatus.OK);
	}		
	
	@GetMapping("/grw/board/bookmark/{userId}")
	public ResponseEntity<?> getBoardList(@PathVariable(value="userId") String userId) {						
		
		List<BoardBookmark> list = boardCommandService.getBookmarkList(userId); 										
							
		return WebControllerUtil
				.getResponse(list											
							,String.format("%d 건 조회되었습니다.", list.size())
							,HttpStatus.OK);
	}
	
	@RequestMapping(value={"/grw/board/bookmark"}, method={RequestMethod.POST,RequestMethod.PUT}) 
	public ResponseEntity<?> saveBoardBookmark(@RequestBody final BoardBookmark entity, BindingResult result) {
									
		if ( result.hasErrors()) {
			throw new ControllerException("오류");
		} 											
		
		boardCommandService.saveBookmark(entity);				
								 					
		return WebControllerUtil
				.getResponse(null											
							,String.format("%d 건 저장되었습니다.", 1)
							,HttpStatus.OK);
	}
	
	@DeleteMapping("/grw/board/bookmark/{id}")
	public ResponseEntity<?> delBoardBookmark(@PathVariable(value="id") Long id) {					
												
		boardCommandService.deleteBookmark(id);							
		
		return WebControllerUtil
				.getResponse(null											
						,String.format("%d 건 삭제되었습니다.", 1)
						,HttpStatus.OK);
	}	
			
}