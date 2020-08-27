package com.like.board.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.like.board.domain.model.BoardBookmark;

@Repository
public interface JpaBoardBookmark extends JpaRepository<BoardBookmark, Long>, QuerydslPredicateExecutor<BoardBookmark> {

}
