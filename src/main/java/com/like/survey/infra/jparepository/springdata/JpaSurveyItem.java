package com.like.survey.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.survey.domain.model.SurveyItem;

@Repository
public interface JpaSurveyItem extends JpaRepository<SurveyItem, Long> {

}
