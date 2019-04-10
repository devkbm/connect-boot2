package com.like.menu.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.menu.domain.model.WebResource;

@Repository
public interface JpaWebResource extends JpaRepository<WebResource, String> {

}
