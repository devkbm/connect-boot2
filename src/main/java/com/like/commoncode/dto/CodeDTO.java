package com.like.commoncode.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.commoncode.domain.model.QCode;
import com.like.commoncode.domain.repository.CodeExpression;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CodeDTO {

	@Data
	public static class SearchCondition implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QCode qCode = QCode.code1;
		
		String id;
		
		String parentId;
			
		String code;
			
		String codeName;
			
		String codeNameAbbreviation;					
					
		Boolean isUse = true;
		
		public BooleanBuilder getCondition() {
			BooleanBuilder builder = new BooleanBuilder();
			
			// 특정 아이디만 검색
			if (StringUtils.hasText(this.id)) {
				builder.and(qCode.id.eq(this.id));
			}
			
			// 특정 아이디의 하위 코드 검색
			if (StringUtils.hasText(this.parentId)) {
				builder.and(qCode.parentCode.id.eq(this.parentId));
			}
			
			if (StringUtils.hasText(this.code)) {
				builder.and(qCode.code.like("%"+this.code+"%"));
			}
			
			if (StringUtils.hasText(this.codeName)) {
				builder.and(qCode.codeName.like("%"+this.codeName+"%"));
			}
			
			if (StringUtils.hasText(this.codeNameAbbreviation)) {
				builder.and(qCode.codeNameAbbreviation.like("%"+this.codeNameAbbreviation+"%"));
			}
			
			if (this.isUse) {																						
				builder.and(qCode.enabled());											
			} 
			
			return builder;
		}
	}
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class CodeSave implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		String id;
		
		String parentId;
			
		String code;
			
		String codeName;
			
		String codeNameAbbreviation;		
						
		LocalDateTime fromDate;
			
		LocalDateTime toDate;			
		
		Integer hierarchyLevel;
				
		Integer seq;
			
		boolean fixedLengthYn;
		
		Integer codeLength;
		
		String cmt;		
	}
	
	
	@Data	
	@NoArgsConstructor(access = AccessLevel.PROTECTED)	
	
	public static class CodeHierarchy implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		String id;
		
		String parentId;
						
		String code;
			
		String codeName;
			
		String codeNameAbbreviation;					
		
		LocalDateTime fromDate;
			
		LocalDateTime toDate;			
		
		int seq;
					
		String cmt;
		
		List<CodeDTO.CodeHierarchy> children = new ArrayList<>();
		
		
		/**
		 * NzTreeNode property 
		 */
		String title;
		
		String key;
				
		@JsonProperty(value="isLeaf") 
		boolean isLeaf;
		
		@QueryProjection
		public CodeHierarchy(LocalDateTime createdDt, String createdBy, LocalDateTime modifiedDt, String modifiedBy,
				String id, String parentId, String code, String codeName, String codeNameAbbreviation, 
				LocalDateTime fromDate, LocalDateTime toDate, int seq, String cmt) {
			super();
			this.createdDt = createdDt;
			this.createdBy = createdBy;
			this.modifiedDt = modifiedDt;
			this.modifiedBy = modifiedBy;
			this.id = id;
			this.parentId = parentId;
			this.code = code;
			this.codeName = codeName;
			this.codeNameAbbreviation = codeNameAbbreviation;
			this.fromDate = fromDate;
			this.toDate = toDate;
			this.seq = seq;
			this.cmt = cmt;
			
			this.title 	= this.codeName + " - " + this.code;
			this.key  	= this.id;
			//this.isLeaf	= this.children.isEmpty() ? true : false;			
		}
		
	}
	
	
}
