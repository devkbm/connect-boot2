package com.like.hrm.payitem.boundary;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.like.hrm.payitem.domain.model.PayTable;
import com.like.hrm.payitem.domain.model.PayTableItem;
import com.like.hrm.payitem.domain.model.QPayTable;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class PayTableDTO {

	@Data
	public static class SearchPayTable implements Serializable {					
		
		private static final long serialVersionUID = 2467975207582687337L;

		private final QPayTable qType = QPayTable.payTable;
								
		private String name;						
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder				
				.and(likeName(this.name));
						
			return builder;
		}			
		
		private BooleanExpression likeName(String name) {
			if (StringUtils.isEmpty(name)) {
				return null;
			}
			
			return qType.name.like("%" + name + "%");
		}
				
	}
	
	@NoArgsConstructor	
	@AllArgsConstructor
	@Getter
	@Builder
	@ToString
	public static class SavePayTable implements Serializable {
			
		private static final long serialVersionUID = -2456770421753675436L;

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
						
		private static final long serialVersionUID = -4174788880355208181L;

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
