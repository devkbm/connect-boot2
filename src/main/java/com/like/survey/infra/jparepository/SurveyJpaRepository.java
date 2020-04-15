package com.like.survey.infra.jparepository;

import org.springframework.stereotype.Repository;

import com.like.survey.domain.model.SurveyForm;
import com.like.survey.domain.repository.SurveyRepository;
import com.like.survey.infra.jparepository.springdata.JpaSurveyForm;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SurveyJpaRepository implements SurveyRepository {
	
	private JPAQueryFactory queryFactory;
	
	private JpaSurveyForm jpaSurveyForm;	
	
	public SurveyJpaRepository(JPAQueryFactory queryFactory
			 				  ,JpaSurveyForm jpaSurveyForm) {
		this.queryFactory = queryFactory;
		this.jpaSurveyForm = jpaSurveyForm;			
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

}
