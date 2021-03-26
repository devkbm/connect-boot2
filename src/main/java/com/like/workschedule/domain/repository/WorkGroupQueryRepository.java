package com.like.workschedule.domain.repository;

import java.util.List;

import com.like.workschedule.boundary.WorkDTO;
import com.like.workschedule.domain.model.WorkGroup;

public interface WorkGroupQueryRepository {

	public List<WorkGroup> getWorkGroupList(WorkDTO.SearchWorkGroup searchCondition);
	
	public List<WorkGroup> getWorkGroupList(String userId);
}
