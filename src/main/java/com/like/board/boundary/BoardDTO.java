package com.like.board.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.QBoard;
import com.like.board.domain.model.enums.BoardType;
import com.like.board.domain.model.vo.Period;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardDTO {
	
	/**
	 * 게시판 조회조건 
	 */
	@Data
	public static class SearchBoard implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QBoard qBoard = QBoard.board;
		
		String boardName;
		
		String boardType;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeBoardName(this.boardName))
				.and(equalBoardType(this.boardType));
						
			
			return builder;
		}
		
		private BooleanExpression likeBoardName(String boardName) {
			if (StringUtils.isEmpty(boardName)) {
				return null;
			}
			
			return qBoard.boardName.like("%"+boardName+"%");
		}
		
		private BooleanExpression equalBoardType(String boardType) {
			if (StringUtils.isEmpty(boardType)) {
				return null;
			}
			
			return qBoard.boardType.eq(BoardType.valueOf(boardType));
		}
	}
	
	/**
	 * 게시판 저장을 위한 DTO Class
	 * 	 
	 */
	@Data
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor	
	public static class SaveBoard implements Serializable {
						
		private static final long serialVersionUID = 1L;

		LocalDateTime createdDt;	
			
		String createdBy;
			
		LocalDateTime modifiedDt;
			
		String modifiedBy;
			
		Long pkBoard;
		
	    /**
	     * 상위 게시판 키
	     */		
	    Long ppkBoard;
			
		/**
		 * 게시판_타입
		 */		
	    String boardType;
		
		/**
	     * 게시판 명
	     */    
		@NotEmpty(message="게시판명은 필수 입력사항입니다.")	
	    String boardName;    
	        
	    String boardDescription;
	            
	    LocalDate fromDate;
	            
	    LocalDate toDate;
	        
	    Boolean useYn;
	    
	    long articleCount;
	            
	    long sequence; 
	    
		public Board newBoard(Board parentBoard) {
			
			return Board.builder()
						.parent(parentBoard)
						.boardName(this.boardName)
						.boardType(BoardType.valueOf(this.boardType))
						.boardDescription(this.boardDescription)
						.period(new Period(this.fromDate, this.toDate))
						.useYn(this.useYn)
						.sequence(this.sequence)
						.build();
		}	
		
		public void modifyBoard(Board board, Board parentBoard) {
			board.modifyEntity(parentBoard
					          ,BoardType.valueOf(this.boardType)
					          ,this.boardName
					          ,this.boardDescription
					          ,new Period(this.fromDate, this.toDate)
					          ,this.useYn
					          ,this.sequence);
		}
		
		public static BoardDTO.SaveBoard convertDTO(Board entity) {					
			
			if (entity == null)
				return null;
			
			Optional<Board> parent = Optional.ofNullable(entity.getParent());
			Optional<Period> period = Optional.ofNullable(entity.getPeriod());
			
			return SaveBoard.builder()
						    .createdDt(entity.getCreatedDt())
						    .createdBy(entity.getCreatedBy())
						    .modifiedDt(entity.getModifiedDt())
						    .modifiedBy(entity.getModifiedBy())
						    .pkBoard(entity.getPkBoard())	
						    .ppkBoard(parent.map(Board::getPkBoard).orElse(null))
						    .boardType(entity.getBoardType().toString())
						    .boardName(entity.getBoardName())
						    .boardDescription(entity.getBoardDescription())						   
						    .fromDate(period.map(Period::getFromDate).orElse(null))
						    .toDate(period.map(Period::getToDate).orElse(null))
						    .useYn(entity.getUseYn())
						    .articleCount(entity.getArticleCount())
						    .sequence(entity.getSequence())
						    .build();	
		}
	}
	
	
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class BoardHierarchy implements Serializable {	
		
		private static final long serialVersionUID = 1L;

		Long key;
		
		Long ppkBoard;
		
		BoardType boardType;
		
		String title;
		
		String boardDescription;
		
		LocalDate fromDate;
		
		LocalDate toDate;
		
		Long articleCount;
		
		Long sequence;
				
		private boolean expanded;
		
		private boolean selected;
		
		@JsonProperty(value="isLeaf")
		private boolean isLeaf;
		
		private boolean active;
		
		private List<BoardDTO.BoardHierarchy> children = new ArrayList<BoardDTO.BoardHierarchy>();
				
		@QueryProjection
		public BoardHierarchy(
				Long key, Long ppkBoard, BoardType boardType, 
				String title, String boardDescription, LocalDate fromDate, 
				LocalDate toDate, Long articleCount, Long sequence) {
			super();
			this.key 				= key;
			this.ppkBoard 			= ppkBoard;
			this.boardType 			= boardType;
			this.title 				= title;
			this.boardDescription 	= boardDescription;
			this.fromDate 			= fromDate;
			this.toDate 			= toDate;
			this.articleCount 		= articleCount;
			this.sequence 			= sequence;
			this.expanded 			= false;
			this.selected 			= false;
			this.active 			= false;
		}
		
				
	}
	
	
}
