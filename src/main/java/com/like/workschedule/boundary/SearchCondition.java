package com.like.workschedule.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.workschedule.domain.model.QSchedule;
import com.like.workschedule.domain.model.QWorkGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchCondition {

	@Data
	public static class WorkGroupSearch implements Serializable {
		
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
		String fkWorkGroup;
		
		@NotEmpty
		String fromDate;
		
		@NotEmpty
		String toDate;
		
		String title;			
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
																				
			builder.and(qSchedule.workGroup.id.in(this.changeIdType(this.fkWorkGroup)));
														
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
		
				
		/**
		 * 콤마로 구분된 id 매개변수를 List<Long>타입으로 변환한다. 
		 * @param params
		 * @return List<Long>
		 */
		private List<Long> changeIdType(String params) {
			
			String idArray[] = params.split(","); 			
		
			List<Long> ids = new ArrayList<Long>(idArray.length);
			
			for (int i=0; i<idArray.length; i++) {
				ids.add(Long.parseLong(idArray[i]));
			}	
			
			return ids;
		}
	}
}
