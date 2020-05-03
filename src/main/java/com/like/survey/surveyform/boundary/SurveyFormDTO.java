package com.like.survey.surveyform.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.survey.surveyform.domain.model.QSurveyForm;
import com.like.survey.surveyform.domain.model.QSurveyItem;
import com.like.survey.surveyform.domain.model.SurveyForm;
import com.like.survey.surveyform.domain.model.SurveyItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SurveyFormDTO {

	@Data
	public static class SearchSurveyForm implements Serializable {
		
		private static final long serialVersionUID = 1130919600828169085L;

		private final QSurveyForm qSurveyForm = QSurveyForm.surveyForm;			
		
		Long formId;
		
		String title;			
				
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(eqFormId(this.formId))
				.and(likeTitle(this.title));
											
			return builder;
		}
		
		private BooleanExpression eqFormId(Long formId) {
			if (formId == null || formId < 0L) {
				return null;
			}
			
			return qSurveyForm.formId.eq(formId);
		}
		
		private BooleanExpression likeTitle(String title) {
			if (StringUtils.isEmpty(title)) {
				return null;
			}
			
			return qSurveyForm.title.like("%"+title+"%");
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
				
		private Long formId;
		
		@NotEmpty	
		private String title;
				
		private String comment;		
		
		public SurveyForm newSurveyForm() {
			return new SurveyForm(null
								 ,this.title
								 ,this.comment
								 ,null);		
		}
		
		public void modifySurveyForm(SurveyForm surveyForm) {
			surveyForm.modifyEntity(this.getTitle()
								   ,this.getComment());			
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SaveSurveyItem implements Serializable {					
		
		private static final long serialVersionUID = -1998861542555719154L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
				
		private Long itemId;
		
		private Long formId;
		
		private String itemType;
		
		private String label;
		
		private String value;
		
		private Boolean required;
		
		private Boolean visible;	
		
		private String comment;
		
		public SurveyItem newSaveSurveyItem(SurveyForm form) {
			return new SurveyItem(form, itemType, label, value, required, null);		
		}
		
		public void modifySaveSurveyItem(SurveyItem surveyItem) {
			surveyItem.modifyEntity(itemType
								   ,label
								   ,value
								   ,required
								   ,visible);			
		}
	}
}
