package com.like.hrm.code.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.hrm.code.domain.model.HrmType;
import com.like.hrm.code.domain.model.QHrmType;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class HrmTypeDTO {

	@Data
	public static class SearchHrmType implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QHrmType qType = QHrmType.hrmType1;
				
		private String hrmType;
		
		private String code;
		
		private String codeName;				
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqHrmType(this.hrmType))
				.and(likeCodeName(this.codeName));
						
			return builder;
		}
		
		private BooleanExpression eqHrmType(String hrmType) {
			if (StringUtils.isEmpty(hrmType)) {
				return null;
			}
			
			return qType.hrmType.eq(HrmTypeEnum.valueOf(hrmType));
		}
		
		private BooleanExpression likeCodeName(String codeName) {
			if (StringUtils.isEmpty(codeName)) {
				return null;
			}
			
			return qType.codeName.like("%" + codeName + "%");
		}
				
	}
	
	@Data	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCode implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;
					
		private String id;
		
		private String hrmType;
		
		private String code;			
			
		private String codeName;					
			
		private boolean useYn;
		
		private Integer sequence;
		
		private String comment;
				
		public HrmType newHrmType() {
			return new HrmType(HrmTypeEnum.valueOf(this.hrmType)
					          ,this.code 
					   		  ,this.codeName
					   		  ,this.useYn
					   		  ,this.sequence
					   		  ,this.comment);
		}
		
		public HrmType changeInfo(HrmType entity) {
			entity.changeInfo(this.codeName
							 ,this.useYn
							 ,this.sequence
							 ,this.comment);
			return entity;
		}
		
		public static SaveCode convert(HrmType entity) {
						
			return new SaveCode(entity.getId()
					           ,entity.getHrmType().toString()
							   ,entity.getCode()
							   ,entity.getCodeName()
							   ,entity.isUseYn()
							   ,entity.getSequence()
							   ,entity.getComment());
			
		}
		
		
	}
}
