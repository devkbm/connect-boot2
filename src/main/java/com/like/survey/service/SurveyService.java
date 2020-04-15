package com.like.survey.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.survey.boundary.SurveyFormDTO;
import com.like.survey.domain.model.SurveyForm;
import com.like.survey.domain.model.SurveyItem;
import com.like.survey.domain.repository.SurveyRepository;

@Service
@Transactional
public class SurveyService {

	private SurveyRepository surveyRepository; 
	
	public SurveyService(SurveyRepository surveyRepository) {
		this.surveyRepository = surveyRepository;
	}
	
	public SurveyForm getSurveyForm(Long id) {
		return surveyRepository.getSurveyForm(id);
	}
	
	public void saveSurveyForm(SurveyFormDTO.SaveSurveyForm dto) {
		SurveyForm entity = null;
		
		if (dto.getId() == null) {
			entity = dto.newSurveyForm();
		} else {
			entity = surveyRepository.getSurveyForm(dto.getId());
			
			dto.modifySurveyForm(entity);
		}
			
		surveyRepository.saveSureyForm(entity);
	}
	
	public void deleteSurveyForm(Long id) {
		SurveyForm entity = surveyRepository.getSurveyForm(id);
		surveyRepository.deleteSurveyForm(entity);
	}
	
	public void saveSurveyItem(SurveyFormDTO.SaveSurveyItem dto) {
		SurveyForm form = surveyRepository.getSurveyForm(dto.getFormId());
		SurveyItem item = null;
		
		if (dto.getId() == null) {
			item = dto.newSaveSurveyItem(form);
			form.addItem(item);
		} else {
			item = form.getItem(dto.getId());
			dto.modifySaveSurveyItem(item);
		}
		
		surveyRepository.saveSureyForm(form);
	}
	
}
