package com.like.hrm.duty.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.vo.Period;
import com.like.holiday.domain.model.DateInfo;
import com.like.holiday.domain.model.DateInfoList;
import com.like.holiday.domain.service.HolidayUtilService;
import com.like.hrm.duty.domain.model.DutyApplication;
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
		
		private List<DutyDate> selectedDate;
		
		public DutyApplication newEntity() {							
			
			return DutyApplication.builder()
								  .dutyId(dutyId)
								  .employeeId(employeeId)
								  .dutyCode(dutyCode)
								  .dutyReason(dutyReason)
								  .period(new Period(dutyStartDateTime, dutyEndDateTime))
								  .selectedDate(this.getSelectedDate())
								  .build();
		}
		
		public void modifyEntity(DutyApplication entity) {
			entity.modifyEntity(dutyCode
							   ,dutyReason
							   ,new Period(dutyStartDateTime, dutyEndDateTime)
							   ,this.getSelectedDate());		
		}
		
		public static SaveDutyApplication convert(DutyApplication entity, HolidayUtilService service) {
			
			DateInfoList dateInfoList = service.getDateInfoList(entity.getPeriod().getFrom().toLocalDate()
															   ,entity.getPeriod().getTo().toLocalDate());
			
			return SaveDutyApplication.builder()
									  .dutyId(entity.getDutyId())
									  .employeeId(entity.getEmployeeId())
									  .dutyCode(entity.getDutyCode())
									  .dutyReason(entity.getDutyReason())
									  .dutyStartDateTime(entity.getPeriod().getFrom())
									  .dutyEndDateTime(entity.getPeriod().getTo())
									  .selectedDate(SaveDutyApplication.convertDutyDate(entity, dateInfoList))
									  .build();
		}
		
		private List<LocalDate> getSelectedDate() {
			return selectedDate.stream().map(e -> e.getDate()).collect(Collectors.toList());
		}
		
		private static List<DutyDate> convertDutyDate(DutyApplication entity, DateInfoList dateInfoList) {
			List<DutyDate> dutyDatelist = new ArrayList<>(dateInfoList.size());
			List<LocalDate> selectedDate = entity.getSelectedDate();					
			
			for (DateInfo date : dateInfoList.getDates()) {
				
				DutyDate d = new DutyDate(date.getDate()
										 ,selectedDate.contains(date.getDate())
										 ,date.isHoliday());
				dutyDatelist.add(d);
			}
			
			return dutyDatelist;			
		}
		
		@Data	
		@AllArgsConstructor
		public static class DutyDate implements Serializable {
			
			LocalDate date;
			
			boolean isSelected;
			
			boolean isHoliday;
		}
		
	}
}
