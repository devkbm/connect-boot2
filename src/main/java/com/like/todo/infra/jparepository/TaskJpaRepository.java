package com.like.todo.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.todo.domain.model.QTask;
import com.like.todo.domain.model.QTaskGroup;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.domain.repository.TaskRepository;
import com.like.todo.infra.jparepository.springdata.JpaTaskGroup;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TaskJpaRepository implements TaskRepository {

	private final QTaskGroup qTaskGroup = QTaskGroup.taskGroup;	
	private final QTask qTask =  QTask.task1;
		
	private JPAQueryFactory  queryFactory;
		
	private JpaTaskGroup jpaTaskGroup;		
	
	public TaskJpaRepository(JPAQueryFactory queryFactory
							,JpaTaskGroup jpaTaskGroup) {
		this.queryFactory = queryFactory;
		this.jpaTaskGroup = jpaTaskGroup;		
	}
	
	@Override
	public TaskGroup getTaskGroup(Long pkTaskGroup) {			
		return jpaTaskGroup.findById(pkTaskGroup).orElse(null);
	}
	
	@Override
	public List<TaskGroup> getTaskGroupList(String userId) {		
		return queryFactory.selectFrom(qTaskGroup)
						   .where(qTaskGroup.createdBy.eq(userId))
						   .fetch();				
	}

	@Override
	public void saveTaskGroup(TaskGroup taskGroup) {
		jpaTaskGroup.save(taskGroup);
	}	

	@Override
	public void deleteTaskGroup(Long pkTaskGroup) {
		jpaTaskGroup.deleteById(pkTaskGroup);
	}	
	
	/*
	@Override
	public List<TaskResultListDTO> getTaskList(TaskQueryDTO taskQueryDTO) {	
		
		return queryFactory.select(new QTaskResultListDTO( 
									qTask.createdDt, qTask.createdBy, qTask.modifiedDt, qTask.modifiedBy,
									qTaskGroup.pkTaskGroup, qTaskGroup.taskGroupName,
									qTask.pkTask, qTask.task, qTask.isCompleted, qTask.dueDate, qTask.comments))
					.from(qTaskGroup)
					.innerJoin(qTaskGroup.taskList,qTask)
					.where(taskQueryDTO.getQueryFilter())
					.fetch();
	}
	*/
	

}
