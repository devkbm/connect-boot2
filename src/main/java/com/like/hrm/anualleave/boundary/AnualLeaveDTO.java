package com.like.hrm.anualleave.boundary;

import java.io.Serializable;
import java.time.LocalDate;

import com.like.hrm.anualleave.domain.model.AnualLeave;
import com.like.hrm.anualleave.domain.model.AnualLeaveId;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AnualLeaveDTO {

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveAnualLeave implements Serializable {
										
		private static final long serialVersionUID = -7210797227903177939L;

		private Integer yyyy;
			
		private String empId;					
								
		private LocalDate base;
			
		private LocalDate from;
		
		private LocalDate to;
		
		private Double cnt;
		
		private Double use_cnt;
		
		private Long total_work_days;
		
		private Long except_days;
		
		private Boolean isIntraAnual;
		
		private String comment;
		
		public AnualLeave newAnualLeave() {
			return new AnualLeave(new AnualLeaveId(yyyy, empId)
								 ,base
								 ,from
								 ,to);
		}
		
		public void modifyEntity(AnualLeave entity) {
								
		}
		
		public static SaveAnualLeave convertDTO(AnualLeave entity) {
			AnualLeaveId id = entity.getId();
			
			return SaveAnualLeave.builder()			
								 .yyyy(id.getYyyy())
								 .empId(id.getEmpId())
								 .base(entity.getBase())
								 .from(entity.getFrom())
								 .to(entity.getTo())
								 .cnt(entity.getCnt())
								 .use_cnt(entity.getUse_cnt())
								 .total_work_days(entity.getTotal_work_days())
								 .except_days(entity.getExcept_days())
								 .isIntraAnual(entity.getIsIntraAnual())
								 .comment(entity.getComment())
						   		 .build();
		}
	}
}
