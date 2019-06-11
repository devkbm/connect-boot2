package com.like.workschedule.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.workschedule.domain.model.QSchedule;
import com.like.workschedule.domain.model.QWorkGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.DateTimePath;
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
		String queryYm;
		
		String title;			
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
								
			builder.and(qSchedule.workGroup.id.eq(this.fkWorkGroup));
					
			log.info(this.queryYm.substring(0, 4));
			log.info(this.queryYm.substring(4, 6));
			
			LocalDateTime param = LocalDateTime.of(Integer.parseInt(this.queryYm.substring(0, 4)), 
													Integer.parseInt(this.queryYm.substring(4, 6)), 
													1, 
													0, 
													0, 
													0);
			
			log.info(param.toString());
			log.info(param.with(TemporalAdjusters.lastDayOfMonth()).toString());
			
			DateTimeExpression<LocalDateTime> monthFirstDay = Expressions.asDateTime(param);
			DateTimeExpression<LocalDateTime> monthEndDay = Expressions.asDateTime(param.with(TemporalAdjusters.lastDayOfMonth()));
			
			
			// LocalDateTime firstDay = param.with(TemporalAdjusters.firstDayOfMonth());
				
																		
			if (this.queryYm != null) {
				builder.and(monthFirstDay.between(qSchedule.start, qSchedule.end)
						.or(monthEndDay.between(qSchedule.start, qSchedule.end))
						.or(qSchedule.start.between(monthFirstDay, monthEndDay))
						.or(qSchedule.end.between(monthFirstDay, monthEndDay)));
				
				/*
					builder.and(qSchedule.start.goe(monthEndDay));
					builder.and(qSchedule.end.loe(monthEndDay));
					builder.and(monthEndDay.between(qSchedule.start, qSchedule.end));
				*/
			}
			
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
	
	
}
