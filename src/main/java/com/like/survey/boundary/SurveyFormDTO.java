package com.like.survey.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.menu.domain.model.QMenuGroup;
import com.like.survey.domain.model.SurveyForm;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SurveyFormDTO {

	@Data
	public static class SearchSurveyForm implements Serializable {

		private static final long serialVersionUID = 4855967336075857695L;

		private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;
		
		String menuGroupCode;
		
		String menuGroupName;
				
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeMenGroupCode(this.menuGroupCode))
				.and(likeMenGroupName(this.menuGroupName));
											
			return builder;
		}
		
		private BooleanExpression likeMenGroupCode(String menuGroupCode) {
			if (StringUtils.isEmpty(menuGroupCode)) {
				return null;
			}
			
			return qMenuGroup.menuGroupCode.like("%"+menuGroupCode+"%");
		}
		
		private BooleanExpression likeMenGroupName(String menuGroupName) {
			if (StringUtils.isEmpty(menuGroupName)) {
				return null;
			}
			
			return qMenuGroup.menuGroupName.like("%"+menuGroupName+"%");
		}
		
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SaveSurveyForm implements Serializable {		
		
		private static final long serialVersionUID = 6673330172202069104L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
				
		private String id;
		
		@NotEmpty	
		private String title;
				
		private String comment;		
		
		public SurveyForm newSurveyForm() {
			return null;	
		}
		
		public void modifySurveyForm(SurveyForm surveyForm) {
			surveyForm.modifyEntity(this.getTitle()
								   ,this.getComment());			
		}
	}
}
