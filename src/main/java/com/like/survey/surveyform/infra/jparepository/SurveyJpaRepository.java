package com.like.survey.surveyform.infra.jparepository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.survey.surveyform.boundary.SurveyFormDTO.SearchSurveyForm;
import com.like.survey.surveyform.domain.model.QSurveyForm;
import com.like.survey.surveyform.domain.model.SurveyForm;
import com.like.survey.surveyform.domain.repository.SurveyRepository;
import com.like.survey.surveyform.infra.jparepository.springdata.JpaSurveyForm;
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

	@Override
	public List<SurveyForm> getSurveyFormList(SearchSurveyForm dto) {

		return queryFactory.selectFrom(QSurveyForm.surveyForm)
						   .where(dto.getBooleanBuilder())
						   .fetch();
	}

}
