package com.like.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.user.domain.model.LogInOutHistory;

@Repository
public interface LogInOutHistoryRepository extends JpaRepository<LogInOutHistory, Long> {

}
