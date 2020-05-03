package com.like.survey.surveyform.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.survey.surveyform.boundary.SurveyFormDTO;
import com.like.survey.surveyform.domain.model.SurveyForm;
import com.like.survey.surveyform.domain.model.SurveyItem;
import com.like.survey.surveyform.domain.repository.SurveyRepository;

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
		
		if (dto.getFormId() == null) {
			entity = dto.newSurveyForm();
		} else {
			entity = surveyRepository.getSurveyForm(dto.getFormId());
			
			dto.modifySurveyForm(entity);
		}
			
		surveyRepository.saveSureyForm(entity);
	}
	
	public void deleteSurveyForm(Long id) {
		SurveyForm entity = surveyRepository.getSurveyForm(id);
		surveyRepository.deleteSurveyForm(entity);
	}
	
	public SurveyItem getSurveyItem(Long formId, Long itemId) {
		return surveyRepository.getSurveyForm(formId).getItem(itemId);
	}
		
	public void saveSurveyItem(SurveyFormDTO.SaveSurveyItem dto) {
		SurveyForm form = surveyRepository.getSurveyForm(dto.getFormId());
		SurveyItem item = null;
		
		if (dto.getItemId() == null) {
			item = dto.newSaveSurveyItem(form);
			form.addItem(item);
		} else {
			item = form.getItem(dto.getItemId());
			dto.modifySaveSurveyItem(item);
		}
		
		surveyRepository.saveSureyForm(form);
	}
	
	public void deleteSurveyItem(Long formId, Long itemId) {
		SurveyForm entity = surveyRepository.getSurveyForm(formId);		
		entity.removeItem(itemId);				
	}
}
