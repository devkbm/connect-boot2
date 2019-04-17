package com.like.workschedule.domain.repository;

import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;

public interface ScheduleRepository {

	public WorkGroup getWorkGroup(Long id);
	
	public void saveWorkGroup(WorkGroup workGroup);
	
	public void deleteWorkGroup(WorkGroup workGroup);
	
	
	public Schedule getSchedule(Long id);
	
	public void saveSchedule(Schedule schedule);
	
	public void deleteSchedule(Schedule schedule);
	
}
