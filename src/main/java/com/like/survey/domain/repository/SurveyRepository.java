package com.like.survey.domain.repository;

import com.like.survey.domain.model.SurveyForm;
import com.like.survey.domain.model.SurveyItem;

public interface SurveyRepository {

	SurveyForm getSurveyForm(Long id);
	
	void saveSureyForm(SurveyForm surveyForm);
	
	void deleteSurveyForm(SurveyForm surveyForm);
	
	SurveyItem getSurveyItem(Long id);
	
	void saveSurveyItem(SurveyItem surveyItem);
	
	void deleteSurveyItem(SurveyItem surveyItem);
}
