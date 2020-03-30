package com.like.hrm.code.boundary;

import java.io.Serializable;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.hrm.code.domain.model.HrmTypeDetailCode;
import com.like.hrm.code.domain.model.QHrmType;
import com.like.hrm.code.domain.model.QHrmTypeDetailCode;
import com.like.hrm.code.domain.model.enums.HrmTypeEnum;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class HrmTypeDetailCodeDTO {

	@Data
	public static class SearchHrmTypeDetailCode implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QHrmTypeDetailCode qType = QHrmTypeDetailCode.hrmTypeDetailCode;
				
		@NotEmpty
		private String typeId;
		
		@Nullable
		private String code;
		
		@Nullable
		private String codeName;				
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqTypeId(this.typeId))
				.and(likeCodeName(this.codeName));
						
			return builder;
		}
		
		private BooleanExpression eqTypeId(String typeId) {			
			
			return qType.typeId.eq(typeId);
		}
		
		private BooleanExpression likeCodeName(String codeName) {
			if (StringUtils.isEmpty(codeName)) {
				return null;
			}
			
			return qType.codeName.like("%" + codeName + "%");
		}
				
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCode implements Serializable {					
				
		private static final long serialVersionUID = -4493967354550706137L;

		private String id;
		
		private String typeId;
		
		private String code;
		
		private String codeName;					
			
		private boolean useYn;
		
		private Integer sequence;
		
		private String comment;
		
		public HrmTypeDetailCode newTypeDetailCode() {
			return new HrmTypeDetailCode(this.typeId
									 ,this.code
							  		 ,this.codeName
							  		 ,this.useYn
							   		 ,this.sequence
							   		 ,this.comment);
		}
			
		public HrmTypeDetailCode changeInfo(HrmTypeDetailCode entity) {
			entity.changeInfo(this.codeName
							 ,this.useYn
							 ,this.sequence
							 ,this.comment);
			return entity;
		}

		public static SaveCode convert(HrmTypeDetailCode entity) {
			if (entity == null) return null;
			
			return new SaveCode(entity.getId()
							   ,entity.getTypeId() 
					           ,entity.getCode()
					           ,entity.getCodeName()
					           ,entity.isUseYn()
					           ,entity.getSequence()
					           ,entity.getComment());			
		}
	}
}
