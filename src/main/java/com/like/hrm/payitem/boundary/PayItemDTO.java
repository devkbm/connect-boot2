package com.like.hrm.payitem.boundary;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import com.like.hrm.payitem.domain.model.PayItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class PayItemDTO {

	@NoArgsConstructor	
	@AllArgsConstructor
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
							  ,seq
							  ,comment
							  ,usePayTable);
		}
		
		public void modifyEntity(PayItem entity) {
			entity.modify(codeName, type, seq, comment, usePayTable);
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
	
	public static class SavePayTable implements Serializable {
	
		private Long id;
		
		private String name;
		
		private Boolean enabled;
		
		private String typeCode1;
		
		private String typeCode2;
		
		private String typeCode3;
		
		private String comment;
	}
	
	public static class SavePayTableItem implements Serializable {
		
		private Long id;
		
		private String code1;
		
		private String code2;
		
		private String code3;
		
		private BigDecimal ammount;
		
		private String comment;
	}
	
	
}
