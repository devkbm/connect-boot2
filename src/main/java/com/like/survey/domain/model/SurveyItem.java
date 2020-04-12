package com.like.survey.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SurveyItem {

	private Long id;
	
	/**
	 * Radio, Checkbox, Text
	 */
	private String itemType;
	
	private String label;
	
	private String value;
	
	private Boolean required;
	
	private Boolean visible;
	
	private List<SurveyOption> options;
	
	public void addOption(SurveyOption option) {
		if (this.options == null) 
			this.options = new ArrayList<>();
		
		this.options.add(option);
	}
	
	public void removeOption(SurveyOption option) {
		this.options.remove(option);
	}
	
	
}
