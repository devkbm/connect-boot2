package com.like.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.user.domain.model.LogInOutHistory;
import com.like.user.domain.repository.LogInOutHistoryRepository;

@Transactional
@Service
public class LogInOutHistoryService {

	private LogInOutHistoryRepository repository;
	
	public LogInOutHistoryService(LogInOutHistoryRepository repository) {
		this.repository = repository;
	}
	
	public void saveLogInOutHistory(LogInOutHistory entity) {
		repository.save(entity);
	}
}
