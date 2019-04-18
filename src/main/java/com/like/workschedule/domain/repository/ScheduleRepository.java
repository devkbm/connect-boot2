package com.like.workschedule.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.dto.WorkDTO;

@Repository
public interface ScheduleRepository {

	public List<WorkGroup> getWorkGroupList(WorkDTO.SearchCondition searchCondition);
	
	public WorkGroup getWorkGroup(Long id);
	
	public void saveWorkGroup(WorkGroup workGroup);
	
	public void deleteWorkGroup(WorkGroup workGroup);
	
	
	public List<Schedule> getScheduleList(WorkDTO.ScheduleSearch searchCondition);
	
	public Schedule getSchedule(Long id);
	
	public void saveSchedule(Schedule schedule);
	
	public void deleteSchedule(Schedule schedule);
	
}
