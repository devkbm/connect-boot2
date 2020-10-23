package com.like.workschedule.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.user.domain.repository.UserRepository;
import com.like.workschedule.boundary.ScheduleDTO;
import com.like.workschedule.boundary.WorkDTO;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.repository.ScheduleRepository;

@Service
@Transactional(readOnly=true)
public class WorkGroupQueryService {
	private final ScheduleRepository scheduleRepository;	
	
	private final UserRepository userRepository;
	
	public WorkGroupQueryService(ScheduleRepository scheduleRepository
						   ,UserRepository userRepository) {
		
		this.scheduleRepository = scheduleRepository;
		this.userRepository = userRepository;
	}
	
	public List<WorkGroup> getWorkGroupList(WorkDTO.SearchWorkGroup searchCondition) {
		return scheduleRepository.getWorkGroupList(searchCondition);		
	}
	
	public List<Schedule> getScheduleList(ScheduleDTO.SearchSchedule searchCondition) {
		return scheduleRepository.getScheduleList(searchCondition);		
	}
	
}
