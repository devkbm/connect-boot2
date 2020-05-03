package com.like.survey.surveyform.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.survey.surveyform.domain.model.SurveyForm;

@Repository
public interface JpaSurveyForm extends JpaRepository<SurveyForm, Long> {

}
