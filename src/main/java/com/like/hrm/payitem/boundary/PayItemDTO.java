package com.like.hrm.payitem.boundary;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.hrm.payitem.domain.model.PayItem;
import com.like.hrm.payitem.domain.model.QPayItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class PayItemDTO {

	
	@Data
	public static class SearchPayItem implements Serializable {		
		
		private static final long serialVersionUID = 2460256856277606586L;

		private final QPayItem qType = QPayItem.payItem;
								
		private String code;
		
		private String codeName;				
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder				
				.and(likeCodeName(this.codeName));
						
			return builder;
		}			
		
		private BooleanExpression likeCodeName(String codeName) {
			if (StringUtils.isEmpty(codeName)) {
				return null;
			}
			
			return qType.codeName.like("%" + codeName + "%");
		}
				
	}
	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Getter
	@Builder
	@ToString
	public static class SavePayItem implements Serializable {		
						
		private static final long serialVersionUID = -4968620080208504061L;

		@NotEmpty
		private String code;

		private String codeName;
		
		private String type;
						
		private Integer seq;
						
		private String comment;
		
		private Boolean usePayTable;
		
		public PayItem newEntity() {
			return new PayItem(code
							  ,codeName
							  ,type
							  ,usePayTable
							  ,seq
							  ,comment);
		}
		
		public void modifyEntity(PayItem entity) {
			entity.modify(codeName, type, usePayTable, seq, comment);
		}
		
		public static SavePayItem convert(PayItem entity) {
			return SavePayItem.builder()
							  .code(entity.getCode())
							  .codeName(entity.getCodeName())
							  .type(entity.getType())
							  .seq(entity.getSeq())
							  .comment(entity.getComment())
							  .usePayTable(entity.getUsePayTable())
							  .build();
		}
		
	}	
	
	
}
