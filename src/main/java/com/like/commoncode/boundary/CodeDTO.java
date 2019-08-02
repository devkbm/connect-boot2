package com.like.commoncode.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CodeDTO {
	
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
