package com.like.user.domain.repository;

import java.util.List;

import com.like.user.domain.model.LogInOutHistory;

public interface LogInOutHistoryRepository {

	List<LogInOutHistory> getLogHistory();
	
	void saveLogHistory(LogInOutHistory entity);	
}
