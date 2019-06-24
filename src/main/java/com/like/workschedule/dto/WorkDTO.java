package com.like.workschedule.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

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
								
			
			LocalDateTime fromDateTime = LocalDateTime.of(Integer.parseInt(this.fromDate.substring(0, 4)), 
														  Integer.parseInt(this.fromDate.substring(4, 6)), 
														  Integer.parseInt(this.fromDate.substring(6, 8)), 
														  0, 
														  0, 
														  0);
			
			LocalDateTime toDateTime = LocalDateTime.of(Integer.parseInt(this.toDate.substring(0, 4)), 
														Integer.parseInt(this.toDate.substring(4, 6)), 
														Integer.parseInt(this.toDate.substring(6, 8)), 
														23, 
														59, 
														59);
			
			//log.info(param.toString());
			//log.info(param.with(TemporalAdjusters.lastDayOfMonth()).toString());
			
			DateTimeExpression<LocalDateTime> fromExpression = Expressions.asDateTime(fromDateTime);
			DateTimeExpression<LocalDateTime> toExpression = Expressions.asDateTime(toDateTime);
			
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
		
		LocalDateTime start;
		
		LocalDateTime end;
		
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
		
		LocalDateTime start;
		
		LocalDateTime end;
		
		Boolean allDay;			
	}
	
	
}
