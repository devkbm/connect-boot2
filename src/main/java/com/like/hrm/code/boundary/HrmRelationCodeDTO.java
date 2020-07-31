package com.like.hrm.code.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;


import com.like.hrm.code.domain.model.HrmRelationCode;
import com.like.hrm.code.domain.model.QHrmRelationCode;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class HrmRelationCodeDTO {

	@Data
	public static class SearchHrmRelationCode implements Serializable {
					
		private static final long serialVersionUID = -8150813705742553938L;

		private final QHrmRelationCode qType = QHrmRelationCode.hrmRelationCode;
				
		private String relCode;						
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqRelCode(this.relCode));
						
			return builder;
		}
		
		private BooleanExpression eqRelCode(String relCode) {
			if (StringUtils.isEmpty(relCode)) {
				return null;
			}
			
			return qType.relCode.eq(relCode);
		}			
				
	}
	
	
}
