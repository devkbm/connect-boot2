package com.like.hrm.duty.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.vo.Period;
import com.like.hrm.duty.domain.model.DutyApplication;
import com.like.hrm.duty.domain.model.DutyApplicationDate;
import com.like.hrm.duty.domain.model.QDutyApplication;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DutyApplicationDTO {

	public static class SearchDutyApplication implements Serializable {
		
		private static final long serialVersionUID = 6850895780318962483L;
		
		private final QDutyApplication qDutyApplication = QDutyApplication.dutyApplication;
		
		String employeeId;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqEmployeeId(this.employeeId));
			
			return builder;
		}
		
		private BooleanExpression eqEmployeeId(String employeeId) {
			if (StringUtils.isEmpty(employeeId)) {
				return null;
			}
			
			return qDutyApplication.employeeId.eq(employeeId);
		}
		
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveDutyApplication implements Serializable {
				
		private static final long serialVersionUID = -6474584560142236686L;

		private Long dutyId;
				
		private String employeeId;
				
		private String dutyCode;
				
		private String dutyReason;
						
		private LocalDateTime dutyStartDateTime;
				
		private LocalDateTime dutyEndDateTime;
		
		private List<LocalDate> selectedDate;
		
		public DutyApplication newEntity() {							
			
			return DutyApplication.builder()
								  .dutyId(dutyId)
								  .employeeId(employeeId)
								  .dutyCode(dutyCode)
								  .dutyReason(dutyReason)
								  .period(new Period(dutyStartDateTime, dutyEndDateTime))
								  .selectedDate(selectedDate)
								  .build();
		}
		
		public void modifyEntity(DutyApplication entity) {
			entity.modifyEntity(dutyCode
							   ,dutyReason
							   ,new Period(dutyStartDateTime, dutyEndDateTime)
							   ,selectedDate);		
		}
		
		public static SaveDutyApplication convert(DutyApplication entity) {
			
			return SaveDutyApplication.builder()
									  .dutyId(entity.getDutyId())
									  .employeeId(entity.getEmployeeId())
									  .dutyCode(entity.getDutyCode())
									  .dutyReason(entity.getDutyReason())
									  .dutyStartDateTime(entity.getPeriod().getFrom())
									  .dutyEndDateTime(entity.getPeriod().getTo())
									  .build();
		}
	}
}
