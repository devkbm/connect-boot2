package com.like.commoncode.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.commoncode.domain.model.Code;
import com.like.commoncode.domain.model.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CodeDTO {
	
	public static CodeDTO.SaveCode convertDTO(Code entity) {					
		
		Code parent = entity.getParentCode();
									
		return SaveCode.builder()
					   .createdDt(entity.getCreatedDt())
					   .createdBy(entity.getCreatedBy())
					   .modifiedDt(entity.getModifiedDt())
					   .modifiedBy(entity.getModifiedBy())
					   .id(entity.getId())
					   .parentId(parent == null ? null : parent.getId())
					   .code(entity.getCode())
					   .codeName(entity.getCodeName())
					   .codeNameAbbreviation(entity.getCodeNameAbbreviation())								
					   .fromDate(entity.getFromDate())
					   .toDate(entity.getToDate())
					   .seq(entity.getSeq())
					   .fixedLengthYn(entity.isFixedLengthYn())
					   .codeLength(entity.getCodeLength())
					   .cmt(entity.getCmt())
					   .build();	
	}
	
	@Data
	public static class SearchCode implements Serializable {
		
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
					
			builder
				.and(equalId(this.id))					// 특정 아이디만 검색
				.and(equalParentId(this.parentId))	 	// 특정 아이디의 하위 코드 검색
				.and(likeCode(this.code))
				.and(likeCodeName(this.codeName))
				.and(likeCodeNameAbbreviation(this.codeNameAbbreviation));
																					
			if (this.isUse) {																						
				builder.and(qCode.enabled());											
			} 
			
			return builder;
		}
		
		private BooleanExpression equalId(String id) {
			if (StringUtils.isEmpty(id)) {
				return null;
			}
			
			return qCode.id.eq(id);
		}
		
		private BooleanExpression equalParentId(String parentId) {
			if (StringUtils.isEmpty(parentId)) {
				return null;
			}
			
			return qCode.parentCode.id.eq(parentId);
		}
		
		private BooleanExpression likeCode(String code) {
			if (StringUtils.isEmpty(code)) {
				return null;
			}
			
			return qCode.code.like("%"+code+"%");
		}
		
		private BooleanExpression likeCodeName(String codeName) {
			if (StringUtils.isEmpty(codeName)) {
				return null;
			}
			
			return qCode.codeName.like("%"+codeName+"%");
		}
		
		private BooleanExpression likeCodeNameAbbreviation(String codeNameAbbreviation) {
			if (StringUtils.isEmpty(codeNameAbbreviation)) {
				return null;
			}
			
			return qCode.codeNameAbbreviation.like("%"+codeNameAbbreviation+"%");
		}
	}
	
	@Data
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SaveCode implements Serializable {
				
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
		
		public Code newCode(Code parentCode) {
						
			return Code.builder()				
					   .parentCode(parentCode)
					   .code(this.code)
					   .codeName(this.codeName)
					   .codeNameAbbreviation(this.codeNameAbbreviation)				
					   .fromDate(this.fromDate)
					   .toDate(this.toDate)
					   .seq(this.seq)
					   .fixedLengthYn(this.fixedLengthYn)
					   .codeLength(this.codeLength)
					   .cmt(this.cmt)
					   .build();
		}
		
		public void modifyCode(Code code) {
			code.modifyEntity(this.codeName
							 ,this.codeNameAbbreviation
							 ,this.fromDate
							 ,this.toDate
							 ,this.seq
							 ,this.fixedLengthYn
							 ,this.codeLength
							 ,this.cmt);
		}
				
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
