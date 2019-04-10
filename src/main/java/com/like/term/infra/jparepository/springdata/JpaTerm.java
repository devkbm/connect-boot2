package com.like.term.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.term.domain.model.TermDictionary;

@Repository
public interface JpaTerm extends JpaRepository<TermDictionary, Long> {

}
