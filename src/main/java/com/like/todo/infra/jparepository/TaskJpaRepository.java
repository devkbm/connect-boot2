package com.like.todo.infra.jparepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.like.todo.domain.model.QTask;
import com.like.todo.domain.model.QTaskGroup;
import com.like.todo.domain.model.Task;
import com.like.todo.domain.model.TaskGroup;
import com.like.todo.domain.repository.TaskRepository;
import com.like.todo.domain.repository.dto.TaskQueryDTO;
import com.like.todo.domain.repository.dto.TaskResultListDTO;
import com.like.todo.infra.jparepository.springdata.JpaTask;
import com.like.todo.infra.jparepository.springdata.JpaTaskGroup;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TaskJpaRepository implements TaskRepository {

	private final QTaskGroup qTaskGroup = QTaskGroup.taskGroup;	
	private final QTask qTask =  QTask.task1;
		
	private JPAQueryFactory  queryFactory;
		
	private JpaTaskGroup jpaTaskGroup;
		
	private JpaTask jpaTask;
	
	public TaskJpaRepository(JPAQueryFactory queryFactory
							,JpaTaskGroup jpaTaskGroup
							,JpaTask jpaTask) {
		this.queryFactory = queryFactory;
		this.jpaTaskGroup = jpaTaskGroup;
		this.jpaTask = jpaTask;
	}
	
	@Override
	public TaskGroup getTaskGroup(Long pkTaskGroup) {			
		return jpaTaskGroup.findById(pkTaskGroup).orElse(null);
	}

	@Override
	public List<TaskGroup> getTaskGroupList() {
		return jpaTaskGroup.findAll();
	}
	
	@Override
	public List<TaskGroup> getTaskGroupList(String userId) {		
		return queryFactory.selectFrom(qTaskGroup)
						   .where(qTaskGroup.modifiedBy.eq(userId))
						   .fetch();				
	}

	@Override
	public void saveTaskGroup(TaskGroup taskGroup) {
		jpaTaskGroup.save(taskGroup);
	}
	
	@Override
	public void saveTaskGroup(List<TaskGroup> taskGroupList) {
		jpaTaskGroup.saveAll(taskGroupList);
	}

	@Override
	public void deleteTaskGroup(Long pkTaskGroup) {
		jpaTaskGroup.deleteById(pkTaskGroup);
	}
	
	@Override
	public void deleteTaskGroup(List<TaskGroup> taskGroupList) {
		jpaTaskGroup.deleteAll(taskGroupList);		
	}

	@Override
	public Task getTask(Long pkTask) {
		return jpaTask.findById(pkTask).orElse(null);
	}

	@Override
	public List<Task> getTaskList() {
		return jpaTask.findAll();
	}
	
	@Override
	public List<TaskResultListDTO> getTaskList(TaskQueryDTO taskQueryDTO) {
		
		return queryFactory.select(Projections.constructor(TaskResultListDTO.class, 
													qTask.createdDt, qTask.createdBy, qTask.modifiedDt, qTask.modifiedBy,
													qTaskGroup.pkTaskGroup, qTaskGroup.taskGroupName,
													qTask.task, qTask.isCompleted, qTask.dueDate, qTask.comments))
							.from(qTaskGroup)
							.innerJoin(qTaskGroup.taskList,qTask)
							.where(taskQueryDTO.getQueryFilter())
							.fetch();			
	}

	@Override
	public void saveTask(Task task) {
		jpaTask.save(task);
	}
		
	@Override
	public void deleteTask(Long pkTask) {
		jpaTask.deleteById(pkTask);
	}

}
