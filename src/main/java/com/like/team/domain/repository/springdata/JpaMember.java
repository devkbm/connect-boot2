package com.like.team.domain.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.team.domain.model.Member;

@Repository
public interface JpaMember extends JpaRepository<Member, String> {

}
