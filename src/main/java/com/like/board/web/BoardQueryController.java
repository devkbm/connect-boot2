package com.like.board.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.board.boundary.BoardDTO;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.enums.BoardType;
import com.like.board.service.BoardQueryService;
import com.like.core.web.util.WebControllerUtil;
import com.like.menu.boundary.EnumDTO;

@RestController
public class BoardQueryController {

	private BoardQueryService boardQueryService;
	
	public BoardQueryController(BoardQueryService boardQueryService) {
		this.boardQueryService = boardQueryService;
	}
	
	@GetMapping("/grw/board/boardType")
	public ResponseEntity<?> getMenuTypeList() {				
		
		List<EnumDTO> list = new ArrayList<>();
		
		for (BoardType boardType : BoardType.values()) {			
			list.add(new EnumDTO(boardType.toString(), boardType.getName()));
		}				 					
								
		return WebControllerUtil.getResponse(list				
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}
	
	@GetMapping("/grw/boardHierarchy")
	public ResponseEntity<?> getBoardHierarchyList() {
											
		List<?> list = boardQueryService.getBoardHierarchy();				 			
		
		return WebControllerUtil.getResponse(list						
											,String.format("%d 건 조회되었습니다.", list.size())
											,HttpStatus.OK);
	}

	@GetMapping("/grw/board")
	public ResponseEntity<?> getBoardList(BoardDTO.SearchBoard dto) {						
		
		List<Board> list = boardQueryService.getBoardList(dto); 										
		List<BoardDTO.SaveBoard> dtoList = list.stream()
											   .map(e -> BoardDTO.SaveBoard.convertDTO(e))
											   .collect(Collectors.toList());
				
		return WebControllerUtil.getResponse(dtoList											
											,String.format("%d 건 조회되었습니다.", dtoList.size())
											,HttpStatus.OK);
	}
}
