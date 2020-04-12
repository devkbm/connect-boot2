package com.like.survey.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SurveyForm {

	private Long id;
	
	private String title;
	
	private String comment;
	
	private List<SurveyItem> items;
	
	public void modifyEntity(String title
							,String comment) {
		this.title = title;
		this.comment = comment;
	}
	
	public void addItem(SurveyItem item) {
		if (this.items == null)
			this.items = new ArrayList<>();
		
		this.items.add(item);
	}
	
	public void removeItem(SurveyItem item) {
		this.items.remove(item);
	}
	
}
