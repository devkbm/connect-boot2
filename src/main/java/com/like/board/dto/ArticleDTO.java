package com.like.board.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.board.domain.model.QArticle;
import com.like.file.dto.FileResponseDTO;
import com.querydsl.core.BooleanBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ArticleDTO {

	@Data
	public static class QueryCondition implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QArticle qArticle = QArticle.article;
		
		Long fkBoard;
		
		String title;
		
		String contents;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(qArticle.board.pkBoard.eq(fkBoard));
			
			if (StringUtils.hasText(this.title)) {
				builder.and(qArticle.title.like("%"+this.title+"%"));
			}
			
			if (StringUtils.hasText(this.contents)) {
				builder.and(qArticle.contents.like("%"+this.contents+"%"));
			}
			
			return builder;
		}
	}
	
	@Data	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Builder
	@ToString
	public static class ArticleSaveMuiltiPart implements Serializable {
		
		private static final long serialVersionUID = -6844786437530688768L;
		
		LocalDateTime createdDt;	
		
		String createdBy;
			
		LocalDateTime modifiedDt;
			
		String modifiedBy;
	    	
	    String pkArticle;	
		
		Long ppkArticle;		
			
		@NotEmpty(message="제목은 필수 입력 사항입니다.")
		String title;
	    	
	    String contents;
	    	
	    String pwd;
	    	
	    int hitCount;
	        
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate fromDate;
	    	
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate toDate;
	    	
	    Integer seq;
	    	
	    Integer depth;
	    	
	    Long fkBoard;
	            
	    @JsonIgnore
	    List<MultipartFile> file = new ArrayList<MultipartFile>();	                	   	    	    	    	  	        
	}
	
	@Data	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Builder
	@ToString
	public static class ArticleSaveJson implements Serializable {
						
		private static final long serialVersionUID = 919127739529051164L;

		LocalDateTime createdDt;	
		
		String createdBy;
			
		LocalDateTime modifiedDt;
			
		String modifiedBy;
	    	
		Long fkBoard;
		
		Long pkArticle;	
		
		Long ppkArticle;		
			
		@NotEmpty(message="제목은 필수 입력 사항입니다.")
		String title;
	    	
	    String contents;
	    	
	    String pwd;
	    	
	    int hitCount;
	        
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate fromDate;
	    	
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate toDate;
	    	
	    Integer seq;
	    	
	    Integer depth;	    		   
	            	    
	    /**
	     * FileInfo 도메인의 PK 리스트
	     */
	    List<String> attachFile;	                	   	    	    	    	  	        
	}
	
	
	@Data	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Builder
	@ToString
	public static class ArticleResponse implements Serializable {
				
		private static final long serialVersionUID = 7795172502919533138L;

		LocalDateTime createdDt;	
		
		String createdBy;
			
		LocalDateTime modifiedDt;
			
		String modifiedBy;
	    	
		Long fkBoard;
		
		Long pkArticle;	
		
		Long ppkArticle;		
			
		@NotEmpty(message="제목은 필수 입력 사항입니다.")
		String title;
	    	
	    String contents;
	    	
	    String pwd;
	    	
	    int hitCount;
	        
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate fromDate;
	    	
	    @DateTimeFormat(pattern = "yyyy-MM-dd")
	    LocalDate toDate;
	    	
	    Integer seq;
	    	
	    Integer depth;	    		   
	            	    
	    List<FileResponseDTO> fileList;	                	   	    	    	    	  	        
	}
}
