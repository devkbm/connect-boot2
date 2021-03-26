package com.like.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {	
			
}