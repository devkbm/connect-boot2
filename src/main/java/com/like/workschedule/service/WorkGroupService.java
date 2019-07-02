package com.like.workschedule.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.user.domain.model.User;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.model.WorkGroupMember;
import com.like.workschedule.domain.model.id.WorkGroupMemberId;
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
	
	public List<WorkGroup> getMyWorkGroupList(String userId) {
		return scheduleRepository.getMyWorkGroupList(userId);	
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
	
	public WorkGroupMember getWorkGroupMember(WorkGroupMemberId id) {
		return scheduleRepository.getWorkGroupMember(id);
	}
	
	public void saveWorkGroupMember(WorkGroup workGroup, User user) {
		scheduleRepository.saveWorkGroup(workGroup);
					
		scheduleRepository.saveWorkGroupMember(new WorkGroupMember(workGroup, user));
	}
	
	public void saveWorkGroupMember(WorkGroup workGroup, List<User> userList) {
		scheduleRepository.saveWorkGroup(workGroup);
		
		for (User user: userList) {
			scheduleRepository.saveWorkGroupMember(new WorkGroupMember(workGroup, user));
		}
	}

	public void deleteWorkGroupMember(WorkGroupMember workGroupMember) {
		scheduleRepository.deleteWorkGroupMember(workGroupMember);
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
