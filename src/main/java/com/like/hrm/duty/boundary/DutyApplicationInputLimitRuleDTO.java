package com.like.hrm.duty.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.hrm.duty.domain.model.DutyApplicationInputLimitRule;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DutyApplicationInputLimitRuleDTO {

	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveDutyApplicationInputLimitRule implements Serializable {					
		
		private static final long serialVersionUID = -3450720454448506614L;

		private Long id;
				
		private String fromYear;
				
		private String fromDate;
				
		private String toYear;
						
		private String toDate;
				
		private Long count;
		
		private String invalidMessage;
		
		private String comment;
		
		public DutyApplicationInputLimitRule newEntity() {							
			
			return DutyApplicationInputLimitRule.builder()	
												.fromYear(this.fromYear)
												.fromDate(this.fromDate)
												.toYear(this.toYear)
												.toDate(this.toDate)
												.count(this.count)
												.invalidMessage(this.invalidMessage)
												.comment(this.comment)												
								  				.build();
		}
		
		public void modifyEntity(DutyApplicationInputLimitRule entity) {
			entity.modifyEntity(entity.getFromYear()
							   ,entity.getFromDate()
							   ,entity.getToYear()
							   ,entity.getToDate()
							   ,entity.getCount()
							   ,entity.getInvalidMessage()
							   ,entity.getComment());		
		}
		
		public static SaveDutyApplicationInputLimitRule convert(DutyApplicationInputLimitRule entity) {
			
			return SaveDutyApplicationInputLimitRule.builder()
													.id(entity.getId())
													.fromYear(entity.getFromYear())
													.fromDate(entity.getFromDate())
													.toYear(entity.getToYear())
													.toDate(entity.getToDate())
													.count(entity.getCount())
													.invalidMessage(entity.getInvalidMessage())
													.comment(entity.getComment())
									  				.build();
		}
			
		
	}
}
