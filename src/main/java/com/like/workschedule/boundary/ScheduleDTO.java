package com.like.workschedule.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.like.workschedule.domain.model.QSchedule;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.Expressions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ScheduleDTO {

	public static SaveSchedule convertDTO(Schedule entity) {
		SaveSchedule dto = SaveSchedule.builder()
									   .id(entity.getId())
									   .title(entity.getTitle())
									   .start(entity.getStart())
									   .end(entity.getEnd())
									   .allDay(entity.getAllDay())
									   .workGroupId(entity.getWorkGroup().getId())
									   .build();
														
		return dto;
	}
	
	public static ScheduleResponse convertResDTO(Schedule entity) {
		
		WorkGroup workGroup = entity.getWorkGroup();
		
		ScheduleResponse dto = ScheduleResponse.builder()
											   .workGroupId(workGroup.getId())
											   .id(entity.getId())
											   .title(entity.getTitle())
											   .color(workGroup.getColor())
											   .start(entity.getStart())
											   .end(entity.getEnd())
											   .allDay(entity.getAllDay())																							
											   .build();
																
		return dto;
	}
	
	@Data
	public static class SearchSchedule implements Serializable {
		
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
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SaveSchedule implements Serializable {
				
		private static final long serialVersionUID = -4732165212710032658L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@Nullable
		Long id;
				
		@NotEmpty
		String title;
		
		OffsetDateTime start;
		
		OffsetDateTime end;
		
		Boolean allDay;
		
		@NotNull
		Long workGroupId;
		
		public Schedule newSchedule(WorkGroup workGroup) {
			return Schedule.builder()
						   .title(this.title)
						   .start(this.start)
						   .end(this.end)
						   .allDay(this.allDay)
						   .workGroup(workGroup)
						   .build();
		}
		
		public void modifySchedule(Schedule schedule) {
			schedule.modifyEntity(title, start, end, allDay);
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class ScheduleResponse implements Serializable {
				
		private static final long serialVersionUID = -8101598433251220343L;

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
