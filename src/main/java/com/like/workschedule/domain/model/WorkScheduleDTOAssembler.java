package com.like.workschedule.domain.model;

import com.like.workschedule.domain.repository.ScheduleRepository;
import com.like.workschedule.dto.WorkDTO;

public class WorkScheduleDTOAssembler {

	public static WorkGroup toEntity(ScheduleRepository repository, WorkDTO.WorkGroupSave dto) {
		WorkGroup entity = null;
		
		if (dto.getId() != null) {
			entity = repository.getWorkGroup(dto.getId());
			entity.name = dto.getName();			
		} else {
			entity = new WorkGroup(dto.getName());
		}
					
		return entity;
	}
	
	public static Schedule toEntity(ScheduleRepository repository, WorkDTO.ScheduleSave dto) {
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
