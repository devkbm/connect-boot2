package com.like.survey.surveyform.domain.repository;

import java.util.List;

import com.like.survey.surveyform.boundary.SurveyFormDTO;
import com.like.survey.surveyform.domain.model.SurveyForm;
import com.like.survey.surveyform.domain.model.SurveyItem;

public interface SurveyRepository {

	SurveyForm getSurveyForm(Long id);
	
	void saveSureyForm(SurveyForm surveyForm);
	
	void deleteSurveyForm(SurveyForm surveyForm);
		
	List<SurveyForm> getSurveyFormList(SurveyFormDTO.SearchSurveyForm dto);
}
