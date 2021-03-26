package com.like.workschedule.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.workschedule.boundary.WorkDTO;
import com.like.workschedule.domain.model.WorkGroup;
import com.like.workschedule.domain.repository.WorkGroupQueryRepository;


@Service
@Transactional(readOnly=true)
public class WorkGroupQueryService {
				
	private WorkGroupQueryRepository repository;
	
	public WorkGroupQueryService(WorkGroupQueryRepository repository) {		
		this.repository = repository;
	}
	
	public List<WorkGroup> getWorkGroupList(WorkDTO.SearchWorkGroup searchCondition) {
		return repository.getWorkGroupList(searchCondition);		
	}
			
	public List<WorkGroup> getMyWorkGroupList(String userId) {
		return repository.getWorkGroupList(userId);
	}
}
