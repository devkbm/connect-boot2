package com.like.workschedule.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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
