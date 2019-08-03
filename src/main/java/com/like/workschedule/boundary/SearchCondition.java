package com.like.workschedule.boundary;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.like.workschedule.domain.model.QSchedule;
import com.like.workschedule.domain.model.QWorkGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
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
			
			builder.and(likeGroupName(this.name));			
			
			return builder;
		}
		
		private BooleanExpression likeGroupName(String name) {
			if (StringUtils.isEmpty(name)) {
				return null;
			}
			
			return qWorkGroup.name.like("%"+this.name+"%");
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
																									
			OffsetDateTime fromDateTime = getFromDate(this.fromDate);			
			OffsetDateTime toDateTime = getToDate(this.toDate);			
			
			DateTimeExpression<OffsetDateTime> fromExpression = Expressions.asDateTime(fromDateTime);
			DateTimeExpression<OffsetDateTime> toExpression = Expressions.asDateTime(toDateTime);
			
			//DateTimeExpression<LocalDateTime> monthEndDay = Expressions.asDateTime(param.with(TemporalAdjusters.lastDayOfMonth()));					
			// LocalDateTime firstDay = param.with(TemporalAdjusters.firstDayOfMonth());																					
			
			builder.and(fromExpression.between(qSchedule.start, qSchedule.end)
						.or(toExpression.between(qSchedule.start, qSchedule.end))
						.or(qSchedule.start.between(fromExpression, toExpression))
						.or(qSchedule.end.between(fromExpression, toExpression)));
				
			builder.and(inWorkgroupIds(this.changeIdType(this.fkWorkGroup)))
			       .and(likeTitle(this.title));
											
			return builder;
		}					
		
		private BooleanExpression inWorkgroupIds(List<Long> ids) {
			if ( CollectionUtils.isEmpty(ids) ) {
				return null;
			}
			
			return qSchedule.workGroup.id.in(ids);
		}
		
		private BooleanExpression likeTitle(String title) {
			if (StringUtils.isEmpty(title)) {
				return null;
			}
			
			return qSchedule.title.like("%"+title+"%");
		}
		
		/**
		 * 콤마로 구분된 id 매개변수를 List<Long>타입으로 변환한다. 
		 * @param params			ex) 1,2,3
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
		
		private OffsetDateTime getFromDate(String fromDate) {
			return OffsetDateTime.of(
					Integer.parseInt(fromDate.substring(0, 4)), 
					Integer.parseInt(fromDate.substring(4, 6)), 
					Integer.parseInt(fromDate.substring(6, 8)), 
					0, 
					0, 
					0,
					0, 
					ZoneOffset.ofHours(9));
		}
		
		private OffsetDateTime getToDate(String toDate) {
			return OffsetDateTime.of(
					Integer.parseInt(toDate.substring(0, 4)), 
					Integer.parseInt(toDate.substring(4, 6)), 
					Integer.parseInt(toDate.substring(6, 8)), 
					23, 
					59, 
					59,
					59, 
					ZoneOffset.ofHours(9));		
		}
	}
}
