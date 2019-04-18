package com.like.workschedule.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.repository.ScheduleRepository;
import com.like.workschedule.dto.WorkDTO;

@Service
@Transactional
public class WorkGroupService {

	@Resource(name="scheduleJpaRepository")
	ScheduleRepository scheduleRepository;	
	
	public List<WorkGroup> getWorkGroupList(WorkDTO.SearchCondition searchCondition) {
		return null;		
	}
	
	/**
	 * 업무그룹를 조회한다.
	 * @param id
	 * @return
	 */
	public WorkGroup getWorkGroup(Long id) {
		return scheduleRepository.getWorkGroup(id);
	}
		
	public void saveWorkGroup(WorkGroup workGroup) {
		scheduleRepository.saveWorkGroup(workGroup);
	}
	
	public void deleteWorkGroup(Long id) {
		WorkGroup entity = scheduleRepository.getWorkGroup(id);
		scheduleRepository.deleteWorkGroup(entity);
	}
	
	public List<Schedule> getScheduleList(WorkDTO.ScheduleSearch searchCondition) {
		return scheduleRepository.getScheduleList(searchCondition);		
	}
	
	public Schedule getSchedule(Long id) {
		return scheduleRepository.getSchedule(id);
	}
	
	public void saveSchedule(Schedule schedule) {
		scheduleRepository.saveSchedule(schedule);
	}
	
}
