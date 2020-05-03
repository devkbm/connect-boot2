package com.like.survey.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.survey.boundary.SurveyFormDTO;
import com.like.survey.domain.model.SurveyForm;
import com.like.survey.domain.repository.SurveyRepository;

@Service
@Transactional(readOnly = true)
public class SurveyQueryService {

	private SurveyRepository surveyRepository; 
	
	public SurveyQueryService(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}
	
	public List<SurveyForm> getSurveyFormList(SurveyFormDTO.SearchSurveyForm dto) {
		return surveyRepository.getSurveyFormList(dto);
	}
}
