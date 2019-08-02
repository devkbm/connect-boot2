package com.like.commoncode.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.commoncode.domain.model.QCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.Data;

public class SearchCondition {

	@Data
	public static class CodeSearch implements Serializable {
		
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
}
