package com.like.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.user.domain.model.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
