package com.like.menu.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.menu.domain.model.WebResource;

@Repository
public interface WebResourceRepository extends JpaRepository<WebResource, String> {

}
