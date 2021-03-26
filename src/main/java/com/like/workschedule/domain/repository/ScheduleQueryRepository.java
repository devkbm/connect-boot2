package com.like.workschedule.domain.repository;

import java.util.List;

import com.like.workschedule.boundary.ScheduleDTO;
import com.like.workschedule.domain.model.Schedule;

public interface ScheduleQueryRepository {

	public List<Schedule> getScheduleList(ScheduleDTO.SearchSchedule searchCondition);
}
