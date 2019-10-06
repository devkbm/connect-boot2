package com.like.workschedule.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.like.user.domain.model.User;
import com.like.user.domain.repository.UserRepository;
import com.like.workschedule.boundary.ScheduleDTO;
import com.like.workschedule.boundary.WorkDTO;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.model.WorkGroupMember;
import com.like.workschedule.domain.model.id.WorkGroupMemberId;
import com.like.workschedule.domain.repository.ScheduleRepository;

@Service
@Transactional
public class WorkGroupService {

	private final ScheduleRepository scheduleRepository;	
	
	private final UserRepository userRepository;
	
	public WorkGroupService(ScheduleRepository scheduleRepository
						   ,UserRepository userRepository) {
		
		this.scheduleRepository = scheduleRepository;
		this.userRepository = userRepository;
	}
				
	
	public List<WorkGroup> getWorkGroupList(WorkDTO.SearchWorkGroup searchCondition) {
		return scheduleRepository.getWorkGroupList(searchCondition);		
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
	
	public void saveWorkGroup(WorkDTO.SaveWorkGroup dto) {
		WorkGroup entity = null;
		
		if (dto.getWorkGroupId() != null) {
			entity = scheduleRepository.getWorkGroup(dto.getWorkGroupId());
		}
		
		if (entity == null) {
			entity = dto.newWorkGroup();
		} else {
			dto.modifyWorkGroup(entity);
		}
		
		List<String> dtoMemberList = dto.getMemberList();
		entity.clearWorkGroupMember();
		
		if (dtoMemberList != null) {
			List<User> userList = userRepository.getUserList(dtoMemberList);
			
			for ( User user: userList ) {
				WorkGroupMember member = new WorkGroupMember(entity, user);				
				entity.addWorkGroupMember(member);
			}
			//workGroupService.saveWorkGroupMember(entity, user);
		}	
		
		
		scheduleRepository.saveWorkGroup(entity);
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
		
	
	public List<Schedule> getScheduleList(ScheduleDTO.SearchSchedule searchCondition) {
		return scheduleRepository.getScheduleList(searchCondition);		
	}
	
	public Schedule getSchedule(Long id) {
		return scheduleRepository.getSchedule(id);
	}
	
	public void saveSchedule(Schedule schedule) {
		scheduleRepository.saveSchedule(schedule);
	}
	
	public void saveSchedule(ScheduleDTO.SaveSchedule dto) {
		WorkGroup workGroup = scheduleRepository.getWorkGroup(dto.getWorkGroupId());
		Schedule entity = scheduleRepository.getSchedule(dto.getId());
		
		if (entity == null) {
			entity = dto.newSchedule(workGroup);
		} else {
			dto.modifySchedule(entity);
		}
		
		scheduleRepository.saveSchedule(entity);
	}
	
	public void deleteSchedule(Long id) {
		Schedule entity = scheduleRepository.getSchedule(id);
		scheduleRepository.deleteSchedule(entity);
	}
	
}
