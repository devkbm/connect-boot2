package com.like.board.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.board.domain.model.Board;

@Repository
public interface JpaBoard extends JpaRepository<Board, Long> {

}
