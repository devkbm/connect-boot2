package com.like.user.infra.jparepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.user.domain.model.LogInOutHistory;
import com.like.user.domain.repository.LogInOutHistoryRepository;
import com.like.user.infra.jparepository.springdata.JpaLogInOutHistory;

@Repository
public class LogInOutHistoryJpaRepository implements LogInOutHistoryRepository {

	@Autowired
	private JpaLogInOutHistory jpaLogInOutHistory;
	
	@Override
	public List<LogInOutHistory> getLogHistory() { 
		return jpaLogInOutHistory.findAll();
	}

	@Override
	public void saveLogHistory(LogInOutHistory entity) {
		jpaLogInOutHistory.save(entity);		
	}
	
}
