package com.like.board.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.board.domain.model.Article;
import com.like.board.domain.model.Board;
import com.like.board.domain.model.QArticle;
import com.like.file.domain.model.FileInfo;
import com.like.file.dto.FileResponseDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.ToString;

public class ArticleDTO {
	
	@Data
	public static class SearchArticle implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QArticle qArticle = QArticle.article;
		
		Long fkBoard;
		
		String title;
		
		String contents;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(qArticle.board.pkBoard.eq(fkBoard))
				.and(likeTitle(this.title))
				.and(likeContents(this.contents));											
			
			return builder;
		}
		
		private BooleanExpression likeTitle(String title) {
			if (StringUtils.isEmpty(title)) {
				return null;
			}
			
			return qArticle.title.like("%"+title+"%");
		}
		
		private BooleanExpression likeContents(String contents) {
			if (StringUtils.isEmpty(contents)) {
				return null;
			}
			
			return qArticle.contents.like("%"+contents+"%");
		}
		
		
	}
	
	@Data	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Builder
	@ToString
	public static class SaveArticleByMuiltiPart implements Serializable {
		
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
	    @Singular(value = "file")
	    List<MultipartFile> file;	 
	    
	    public Article newArticle(Board board) {
			
			return Article.builder()	
						  .board(board)
						  .ppkArticle(this.ppkArticle)
						  .title(this.title)
						  .contents(this.contents)
						  .fromDate(this.fromDate)
						  .toDate(this.toDate)
						  .pwd(this.pwd)
						  .build();
		}
	    
	    public void modifyArticle(Article entity) {
			
	    	entity.modifyEntity(title
	    					   ,contents
	    					   ,fromDate
	    					   ,toDate
	    					   ,seq);								
		}
	}
	
	@Data	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Builder
	@ToString
	public static class SaveArticleByJson implements Serializable {
						
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
	    
	    public Article newArticle(Board board) {
			
			return Article.builder()	
						  .board(board)
						  .ppkArticle(this.ppkArticle)
						  .title(this.title)
						  .contents(this.contents)
						  .fromDate(this.fromDate)
						  .toDate(this.toDate)
						  .pwd(this.pwd)
						  .build();
		}
	    
	    public void modifyArticle(Article entity) {
			
	    	entity.modifyEntity(title
	    					   ,contents
	    					   ,fromDate
	    					   ,toDate
	    					   ,seq);								
		}
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
	    
		String userName;
		
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
	    
	    Boolean editable;
	    
	    public static ArticleDTO.ArticleResponse converDTO(Article entity) {
			
			List<FileInfo> fileInfoList = entity.getAttachedFileInfoList();
			List<FileResponseDTO> responseList = new ArrayList<>();
			
			for (FileInfo fileInfo : fileInfoList) {
				FileResponseDTO dto = FileResponseDTO.builder()
													 .url(fileInfo.getPkFile())
													 .name(fileInfo.getFileName())
													 .status("done")																									
													 .url("http://localhost:8090/common/file/"+fileInfo.getPkFile())
													 .build();
				
				responseList.add(dto);				
			}
																																															
			return ArticleDTO.ArticleResponse
							 .builder()
							 .createdDt(entity.getCreatedDt())
							 .createdBy(entity.getCreatedBy())
							 .modifiedDt(entity.getModifiedDt())
							 .modifiedBy(entity.getModifiedBy())
							 .pkArticle(entity.getPkArticle())
							 .ppkArticle(entity.getPpkArticle())
							 .userName(entity.getUserName())
							 .fkBoard(entity.getBoard().getPkBoard())				
							 .title(entity.getTitle())
							 .contents(entity.getContents())
							 .fileList(responseList)			
							 .editable(entity.getEditable())
							 .build();
		}
	}
}
