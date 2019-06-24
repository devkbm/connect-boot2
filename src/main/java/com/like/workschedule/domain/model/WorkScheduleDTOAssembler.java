package com.like.workschedule.domain.model;

import java.util.stream.Collectors;

import com.like.workschedule.domain.repository.ScheduleRepository;
import com.like.workschedule.dto.WorkDTO;
import com.like.workschedule.dto.WorkDTO.ScheduleResponse;
import com.like.workschedule.dto.WorkDTO.ScheduleSave;
import com.like.workschedule.dto.WorkDTO.WorkGroupSave;

public class WorkScheduleDTOAssembler {

	public static WorkGroup toEntity(WorkDTO.WorkGroupSave dto, ScheduleRepository repository) {
		WorkGroup entity = null;
		
		if (dto.getWorkGroupId() != null) {
			entity = repository.getWorkGroup(dto.getWorkGroupId());
			entity.name = dto.getWorkGroupName();	
			entity.color = dto.getColor();
		} else {
			entity = new WorkGroup(dto.getWorkGroupName());
			entity.color = dto.getColor();
		}
					
		return entity;
	}
	
	public static WorkDTO.WorkGroupSave convertDTO(WorkGroup entity) {
		WorkDTO.WorkGroupSave dto = WorkGroupSave.builder()
												.workGroupId(entity.getId())
												.workGroupName(entity.getName())
												.color(entity.getColor())
												.memberList(entity.memberList.stream().map( r -> r.getUser().getUserId()).collect(Collectors.toList()))
												.build();
		
		return dto;
	}
	
	
	public static Schedule toEntity(WorkDTO.ScheduleSave dto, ScheduleRepository repository) {
		Schedule entity = null;
		WorkGroup workGroup = null;
		
		if (dto.getId() != null) {
			entity = repository.getSchedule(dto.getId());			
			entity.title 	= dto.getTitle();
			entity.start 	= dto.getStart();
			entity.end 		= dto.getEnd();
			entity.allDay 	= dto.getAllDay();					
		} else {
			workGroup = repository.getWorkGroup(dto.getWorkGroupId());
			
			entity = Schedule.builder()
							 .title(dto.getTitle())
							 .start(dto.getStart())
							 .end(dto.getEnd())
							 .allDay(dto.getAllDay())
							 .workGroup(workGroup)
							 .build();
		}
					
		return entity;
	}
	
	public static ScheduleSave convertDTO(Schedule entity) {
		WorkDTO.ScheduleSave dto = ScheduleSave.builder()
												.id(entity.id)
												.title(entity.title)
												.start(entity.start)
												.end(entity.end)
												.allDay(entity.allDay)
												.workGroupId(entity.getWorkGroup().getId())
												.build();
														
		return dto;
	}
	
	public static ScheduleResponse convertResDTO(Schedule entity) {
		
		WorkGroup workGroup = entity.getWorkGroup();
		
		WorkDTO.ScheduleResponse dto = ScheduleResponse.builder()
												.workGroupId(workGroup.getId())
												.id(entity.id)
												.title(entity.title)
												.color(workGroup.getColor())
												.start(entity.start)
												.end(entity.end)
												.allDay(entity.allDay)																							
												.build();
														
		return dto;
	}
	
}
