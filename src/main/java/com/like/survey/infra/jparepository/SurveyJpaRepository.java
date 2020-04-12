package com.like.survey.infra.jparepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.survey.domain.model.SurveyForm;
import com.like.survey.domain.model.SurveyItem;
import com.like.survey.domain.repository.SurveyRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SurveyJpaRepository implements SurveyRepository {

	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Override
	public SurveyForm getSurveyForm(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSureyForm(SurveyForm surveyForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSurveyForm(SurveyForm surveyForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SurveyItem getSurveyItem(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSurveyItem(SurveyItem surveyItem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSurveyItem(SurveyItem surveyItem) {
		// TODO Auto-generated method stub
		
	}

}
