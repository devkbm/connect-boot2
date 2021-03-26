package com.like.workschedule.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.workschedule.boundary.ScheduleDTO;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.repository.ScheduleRepository;
import com.like.workschedule.domain.repository.WorkGroupRepository;

@Service
@Transactional
public class ScheduleService {

	private ScheduleRepository repository;
	private WorkGroupRepository workGroupRepository;
	
	public ScheduleService(ScheduleRepository repository
						  ,WorkGroupRepository workGroupRepository) {
		this.repository = repository;
	}
	
	public Schedule getSchedule(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void saveSchedule(Schedule schedule) {
		repository.save(schedule);
	}
	
	public void saveSchedule(ScheduleDTO.SaveSchedule dto) {
		WorkGroup workGroup = workGroupRepository.findById(dto.getWorkGroupId()).orElse(null);
		Schedule entity = null; 
		
		if (dto.getId() != null) {
			entity = repository.findById(dto.getId()).orElse(null);
		}
		
		if (entity == null) {
			entity = dto.newSchedule(workGroup);
		} else {
			dto.modifySchedule(entity);
		}
		
		repository.save(entity);
	}
	
	public void deleteSchedule(Long id) {		
		repository.deleteById(id);
	}
}
