package com.like.workschedule.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.like.workschedule.boundary.ScheduleDTO;
import com.like.workschedule.boundary.WorkDTO;
import com.like.workschedule.domain.model.QWorkGroup;
import com.like.workschedule.domain.model.QWorkGroupMember;
import com.like.workschedule.domain.model.Schedule;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.model.WorkGroupMember;
import com.like.workschedule.domain.model.id.WorkGroupMemberId;
import com.like.workschedule.domain.repository.ScheduleRepository;
import com.like.workschedule.infra.jparepository.springdata.JpaSchedule;
import com.like.workschedule.infra.jparepository.springdata.JpaWorkGroup;
import com.like.workschedule.infra.jparepository.springdata.JpaWorkGroupMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ScheduleJpaRepository implements ScheduleRepository {

	@Autowired
	private JPAQueryFactory  queryFactory;	
	
	@Autowired
	private JpaWorkGroup jpaWorkGroup;
	
	@Autowired
	private JpaWorkGroupMember jpaWorkGroupMember;
	
	@Autowired
	private JpaSchedule jpaSchedule;	
	
	private final QWorkGroup qWorkGroup = QWorkGroup.workGroup;
	private final QWorkGroupMember qWorkGroupMember = QWorkGroupMember.workGroupMember;	
	
	@Override
	public List<WorkGroup> getWorkGroupList(WorkDTO.SearchWorkGroup searchCondition) {
		return Lists.newArrayList(jpaWorkGroup.findAll(searchCondition.getBooleanBuilder()));
	}
	
	@Override
	public List<WorkGroup> getMyWorkGroupList(String userId) {
		
		/*
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(JPAExpressions.select(Expressions.constant(1))
									.from(qWorkGroupMember)
									.where(qWorkGroupMember.user.userId.eq(userId)).exists()
			);				
		
		return Lists.newArrayList(jpaWorkGroup.findAll(builder));
		*/
		
		/*
		 		return queryFactory.select(qArticle)
							.from(qArticle)	
							.leftJoin(qArticle.files, qAttachedFile)
							.fetchJoin()
							.where(qArticle.board.pkBoard.eq(fkBoard))							
							.fetch();	
		 */
		
		
		/*
		return queryFactory.select(qWorkGroup).distinct()
							.from(qWorkGroup)							
							.innerJoin(qWorkGroup.scheduleList, qSchedule)
							.fetchJoin()							
							.where(JPAExpressions.select(Expressions.constant(1))
												 .from(qWorkGroupMember)
												 .where(qWorkGroupMember.user.userId.eq(userId)).exists())
							.fetch();		
		*/
		
		return queryFactory.select(qWorkGroup).distinct()
				.from(qWorkGroup)							
				.innerJoin(qWorkGroup.memberList, qWorkGroupMember)
				.fetchJoin()
				.where(qWorkGroupMember.user.userId.eq(userId))				
				.fetch();
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
	public List<Schedule> getScheduleList(ScheduleDTO.SearchSchedule searchCondition) {
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
