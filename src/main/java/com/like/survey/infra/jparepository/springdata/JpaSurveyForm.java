package com.like.survey.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.survey.domain.model.SurveyForm;

@Repository
public interface JpaSurveyForm extends JpaRepository<SurveyForm, Long> {

}
