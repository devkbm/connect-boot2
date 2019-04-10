package com.like.board.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.board.domain.model.AttachedFile;

@Repository
public interface JpaAttachedFile extends JpaRepository<AttachedFile, Long> {
	
}
