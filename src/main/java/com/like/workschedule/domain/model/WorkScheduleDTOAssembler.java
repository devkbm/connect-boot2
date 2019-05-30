package com.like.workschedule.domain.model;

import java.util.stream.Collectors;

import com.like.workschedule.domain.repository.ScheduleRepository;
import com.like.workschedule.dto.WorkDTO;
import com.like.workschedule.dto.WorkDTO.WorkGroupSave;

public class WorkScheduleDTOAssembler {

	public static WorkGroup toEntity(WorkDTO.WorkGroupSave dto, ScheduleRepository repository) {
		WorkGroup entity = null;
		
		if (dto.getWorkGroupId() != null) {
			entity = repository.getWorkGroup(dto.getWorkGroupId());
			entity.name = dto.getWorkGroupName();			
		} else {
			entity = new WorkGroup(dto.getWorkGroupName());
		}
					
		return entity;
	}
	
	public static WorkDTO.WorkGroupSave convertDTO(WorkGroup entity) {
		WorkDTO.WorkGroupSave dto = WorkGroupSave.builder()
												.workGroupId(entity.getId())
												.workGroupName(entity.getName())
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
			workGroup = repository.getWorkGroup(dto.getFkWorkGroup());
			
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
	
}
