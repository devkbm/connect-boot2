package com.like.survey.infra.jparepository;

import org.springframework.stereotype.Repository;

import com.like.survey.domain.model.SurveyForm;
import com.like.survey.domain.model.SurveyItem;
import com.like.survey.domain.repository.SurveyRepository;
import com.like.survey.infra.jparepository.springdata.JpaSurveyForm;
import com.like.survey.infra.jparepository.springdata.JpaSurveyItem;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SurveyJpaRepository implements SurveyRepository {
	
	private JPAQueryFactory queryFactory;
	
	private JpaSurveyForm jpaSurveyForm;
	
	private JpaSurveyItem jpaSurveyItem;
	
	public SurveyJpaRepository(JPAQueryFactory queryFactory
			 				  ,JpaSurveyForm jpaSurveyForm
			 				  ,JpaSurveyItem jpaSurveyItem) {
		this.queryFactory = queryFactory;
		this.jpaSurveyForm = jpaSurveyForm;
		this.jpaSurveyItem = jpaSurveyItem;		
	}
	
	@Override
	public SurveyForm getSurveyForm(Long id) {
		return jpaSurveyForm.findById(id).orElse(null);
	}

	@Override
	public void saveSureyForm(SurveyForm surveyForm) {
		jpaSurveyForm.save(surveyForm);		
	}

	@Override
	public void deleteSurveyForm(SurveyForm surveyForm) {
		jpaSurveyForm.delete(surveyForm);		
	}

	@Override
	public SurveyItem getSurveyItem(Long id) {		
		return jpaSurveyItem.findById(id).orElse(null);
	}

	@Override
	public void saveSurveyItem(SurveyItem surveyItem) {
		jpaSurveyItem.save(surveyItem);
		
	}

	@Override
	public void deleteSurveyItem(SurveyItem surveyItem) {
		jpaSurveyItem.delete(surveyItem);		
	}

}
