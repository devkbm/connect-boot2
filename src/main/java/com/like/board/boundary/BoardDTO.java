package com.like.board.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.QBoard;
import com.like.board.domain.model.enums.BoardType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BoardDTO {
	
	/**
	 * 게시판 저장을 위한 DTO Class
	 * 	 
	 */
	@Data
	@Builder
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor	
	public static class BoardSaveDTO implements Serializable {
						
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
