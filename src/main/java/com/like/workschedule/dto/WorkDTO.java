package com.like.workschedule.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.like.workschedule.domain.model.QSchedule;
import com.like.workschedule.domain.model.QWorkGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class WorkDTO {

	@Data
	public static class SearchCondition implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QWorkGroup qWorkGroup = QWorkGroup.workGroup;
						
		String name;			
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
									
			if (StringUtils.hasText(this.name)) {
				builder.and(qWorkGroup.name.like("%"+this.name+"%"));
			}						
			
			return builder;
		}
	}
	
	@Data
	public static class ScheduleSearch implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QSchedule qSchedule = QSchedule.schedule;
		
		@NotEmpty
		Long fkWorkGroup;
		
		@NotEmpty
		String fromDate;
		
		@NotEmpty
		String toDate;
		
		String title;			
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
								
			builder.and(qSchedule.workGroup.id.eq(this.fkWorkGroup));
								
			
			OffsetDateTime fromDateTime = OffsetDateTime.of(
											LocalDateTime.of(Integer.parseInt(this.fromDate.substring(0, 4)), 
														  Integer.parseInt(this.fromDate.substring(4, 6)), 
														  Integer.parseInt(this.fromDate.substring(6, 8)), 
														  0, 
														  0, 
														  0), ZoneOffset.ofHours(9));
			
			OffsetDateTime toDateTime = OffsetDateTime.of(
											LocalDateTime.of(Integer.parseInt(this.toDate.substring(0, 4)), 
														Integer.parseInt(this.toDate.substring(4, 6)), 
														Integer.parseInt(this.toDate.substring(6, 8)), 
														23, 
														59, 
														59), ZoneOffset.ofHours(9));
			
			//log.info(param.toString());
			//log.info(param.with(TemporalAdjusters.lastDayOfMonth()).toString());
			
			DateTimeExpression<OffsetDateTime> fromExpression = Expressions.asDateTime(fromDateTime);
			DateTimeExpression<OffsetDateTime> toExpression = Expressions.asDateTime(toDateTime);
			
			//DateTimeExpression<LocalDateTime> monthEndDay = Expressions.asDateTime(param.with(TemporalAdjusters.lastDayOfMonth()));					
			// LocalDateTime firstDay = param.with(TemporalAdjusters.firstDayOfMonth());
				
																		

			builder.and(fromExpression.between(qSchedule.start, qSchedule.end)
					.or(toExpression.between(qSchedule.start, qSchedule.end))
					.or(qSchedule.start.between(fromExpression, toExpression))
					.or(qSchedule.end.between(fromExpression, toExpression)));
				
			
			if (StringUtils.hasText(this.title)) {
				builder.and(qSchedule.title.like("%"+this.title+"%"));
			}						
			
			return builder;
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class WorkGroupSave implements Serializable {
				
		LocalDateTime createdDt;	 
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		Long workGroupId;
				
		String workGroupName;		
		
		String color;
		
		List<String> memberList;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ScheduleSave implements Serializable {
				
		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		Long id;
				
		String title;
		
		OffsetDateTime start;
		
		OffsetDateTime end;
		
		Boolean allDay;
		
		Long workGroupId;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ScheduleResponse implements Serializable {
				
		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		Long workGroupId;
		
		Long id;
				
		String title;
		
		String color;
				
		OffsetDateTime start;
				
		OffsetDateTime end;
		
		Boolean allDay;			
	}
	
	
}
