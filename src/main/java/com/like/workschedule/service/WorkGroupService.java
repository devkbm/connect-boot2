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
import com.like.workschedule.domain.repository.WorkGroupMemberRepository;
import com.like.workschedule.domain.repository.WorkGroupRepository;

@Service
@Transactional
public class WorkGroupService {

	private WorkGroupRepository repository;
	private WorkGroupMemberRepository workGroupMemberRepository;
	private UserRepository userRepository;
	
	public WorkGroupService(WorkGroupRepository repository
						   ,WorkGroupMemberRepository workGroupMemberRepository	
						   ,UserRepository userRepository) {		
		this.repository = repository;
		this.workGroupMemberRepository = workGroupMemberRepository;
		this.userRepository = userRepository;
	}
					
	public List<WorkGroup> getMyWorkGroupList(String userId) {
		return null;
	}
	
	/**
	 * 업무그룹를 조회한다.
	 * @param id
	 * @return
	 */
	public WorkGroup getWorkGroup(Long id) {
		return repository.findById(id).orElse(null);
	}			
	
	public void saveWorkGroup(WorkDTO.SaveWorkGroup dto) {
		WorkGroup entity = null;
		
		if (dto.getWorkGroupId() != null) {
			entity = repository.findById(dto.getWorkGroupId()).orElse(null);
		}
		
		if (entity == null) {
			entity = dto.newWorkGroup();
		} else {
			dto.modifyWorkGroup(entity);
		}
		
		List<String> dtoMemberList = dto.getMemberList();
		entity.clearWorkGroupMember();
		
		if (dtoMemberList != null) {
			List<User> userList = userRepository.findAllById(dtoMemberList);
			
			for ( User user: userList ) {
				WorkGroupMember member = new WorkGroupMember(entity, user);				
				entity.addWorkGroupMember(member);
			}
			//workGroupService.saveWorkGroupMember(entity, user);
		}	
				
		repository.save(entity);
	}
	
	public void deleteWorkGroup(Long id) {
		repository.deleteById(id);
	}
	
	public WorkGroupMember getWorkGroupMember(WorkGroupMemberId id) {
		return workGroupMemberRepository.findById(id).orElse(null);
	}
	
	public void saveWorkGroupMember(WorkGroup workGroup, User user) {
		//scheduleRepository.saveWorkGroup(workGroup);
					
		///scheduleRepository.saveWorkGroupMember(new WorkGroupMember(workGroup, user));
	}
	
	public void saveWorkGroupMember(WorkGroup workGroup, List<User> userList) {
		repository.save(workGroup);
		
		for (User user: userList) {
			//scheduleRepository.saveWorkGroupMember(new WorkGroupMember(workGroup, user));
		}
	}

	public void deleteWorkGroupMember(WorkGroupMember workGroupMember) {
		workGroupMemberRepository.delete(workGroupMember);
	}
		

	
}
