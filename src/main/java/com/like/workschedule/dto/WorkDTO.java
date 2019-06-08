package com.like.workschedule.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.util.StringUtils;

import com.like.workschedule.domain.model.QSchedule;
import com.like.workschedule.domain.model.QWorkGroup;
import com.querydsl.core.BooleanBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
		
		Long fkWorkGroup;
		
		String title;			
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
								
			builder.and(qSchedule.workGroup.id.eq(this.fkWorkGroup));
			
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
		
		Instant start;
		
		Instant end;
		
		Boolean allDay;
		
		Long workGroupId;
	}
	
	
}
