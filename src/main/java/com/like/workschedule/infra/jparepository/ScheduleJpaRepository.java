package com.like.workschedule.infra.jparepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.repository.ScheduleRepository;
import com.like.workschedule.infra.jparepository.springdata.JpaSchedule;
import com.like.workschedule.infra.jparepository.springdata.JpaWorkGroup;

public class ScheduleJpaRepository implements ScheduleRepository {

	@Autowired
	private JpaWorkGroup jpaWorkGroup;
	
	@Autowired
	private JpaSchedule jpaSchedule;
	
	
	@Override
	public WorkGroup getWorkGroup(Long id) {
		Optional<WorkGroup> entity = jpaWorkGroup.findById(id);
		return entity.isPresent() ? entity.get() : null;
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
	public Schedule getSchedule(Long id) {
		Optional<Schedule> entity = jpaSchedule.findById(id);
		return entity.isPresent() ? entity.get() : null;
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
