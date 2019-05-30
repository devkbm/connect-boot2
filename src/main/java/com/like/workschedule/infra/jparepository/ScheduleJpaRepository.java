package com.like.workschedule.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.model.WorkGroupMember;
import com.like.workschedule.domain.model.id.WorkGroupMemberId;
import com.like.workschedule.domain.repository.ScheduleRepository;
import com.like.workschedule.dto.WorkDTO.ScheduleSearch;
import com.like.workschedule.dto.WorkDTO.SearchCondition;
import com.like.workschedule.infra.jparepository.springdata.JpaSchedule;
import com.like.workschedule.infra.jparepository.springdata.JpaWorkGroup;
import com.like.workschedule.infra.jparepository.springdata.JpaWorkGroupMember;

@Repository
public class ScheduleJpaRepository implements ScheduleRepository {

	@Autowired
	private JpaWorkGroup jpaWorkGroup;
	
	@Autowired
	private JpaWorkGroupMember jpaWorkGroupMember;
	
	@Autowired
	private JpaSchedule jpaSchedule;	
	
	@Override
	public List<WorkGroup> getWorkGroupList(SearchCondition searchCondition) {
		return Lists.newArrayList(jpaWorkGroup.findAll(searchCondition.getBooleanBuilder()));
	}
	
	@Override
	public WorkGroup getWorkGroup(Long id) {
		Optional<WorkGroup> entity = jpaWorkGroup.findById(id);
		return entity.orElse(null);
	}

	@Override
	public void saveWorkGroup(WorkGroup workGroup) {
		jpaWorkGroup.save(workGroup);		
	}

	@Override
	public void deleteWorkGroup(WorkGroup workGroup) {
		jpaWorkGroup.delete(workGroup);		
	}
	
	@Override
	public WorkGroupMember getWorkGroupMember(WorkGroupMemberId id) {
		Optional<WorkGroupMember> entity = jpaWorkGroupMember.findById(id);
		return entity.orElse(null);
	}

	@Override
	public void saveWorkGroupMember(WorkGroupMember workGroupMember) {
		jpaWorkGroupMember.save(workGroupMember);
	}

	@Override
	public void deleteWorkGroupMember(WorkGroupMember workGroupMember) {
		jpaWorkGroupMember.delete(workGroupMember);		
	}
	
	@Override
	public List<Schedule> getScheduleList(ScheduleSearch searchCondition) {
		return Lists.newArrayList(jpaSchedule.findAll(searchCondition.getBooleanBuilder()));
	}

	@Override
	public Schedule getSchedule(Long id) {
		Optional<Schedule> entity = jpaSchedule.findById(id);
		return entity.orElse(null);
	}

	@Override
	public void saveSchedule(Schedule schedule) {
		jpaSchedule.save(schedule);		
	}

	@Override
	public void deleteSchedule(Schedule schedule) {
		jpaSchedule.delete(schedule);		
	}

}
