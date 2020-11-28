package com.like.hrm.payitem.boundary;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import com.like.hrm.payitem.domain.model.PayItem;
import com.like.hrm.payitem.domain.model.PayTable;
import com.like.hrm.payitem.domain.model.PayTableItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class PayItemDTO {

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
	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Getter
	@Builder
	@ToString
	public static class SavePayTable implements Serializable {
	
		private Long id;
		
		private String name;
		
		private Boolean enabled;
		
		private String typeCode1;
		
		private String typeCode2;
		
		private String typeCode3;
		
		private String comment;
		
		public PayTable newEntity() {
			return new PayTable(name
							   ,enabled
							   ,typeCode1
							   ,typeCode2
							   ,typeCode3
							   ,comment);
		}
		
		public void modifyEntity(PayTable entity) {
			entity.modify(name, enabled, typeCode1, typeCode2, typeCode3, comment);
		}
		
		public static SavePayTable convert(PayTable entity) {
			return SavePayTable.builder()
							   .id(entity.getId())
							   .name(entity.getName())
							   .build();
		}
	}
	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Getter
	@Builder
	@ToString
	public static class SavePayTableItem implements Serializable {
		
		@NotEmpty
		private Long payTableId;
		
		private Long id;
		
		private String code1;
		
		private String code2;
		
		private String code3;
		
		private BigDecimal ammount;
		
		private String comment;
		
		public PayTableItem newEntity(PayTable payTable) {
			return new PayTableItem(payTable
							       ,code1
							       ,code2
							       ,code3
							       ,ammount
							       ,comment);
		}
		
		public void modifyEntity(PayTableItem entity) {
			entity.modify(ammount, comment);
		}
		
		public static SavePayTableItem convert(PayTableItem entity) {
			Long payTableId = entity.getPayTable() != null ? entity.getPayTable().getId() : null;
			
			return SavePayTableItem.builder()	
								   .payTableId(payTableId)
								   .id(entity.getId())
								   .code1(entity.getCode1())
								   .code2(entity.getCode2())
								   .code3(entity.getCode3())
								   .ammount(entity.getAmmount())
								   .comment(entity.getComment())
								   .build();
		}
		
	}
	
	
}
