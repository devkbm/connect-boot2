package com.like.survey.domain.model;

import java.util.List;

public class SurveyItem {

	private String id;
	
	/**
	 * Radio, Checkbox, Text
	 */
	private String itemType;
	
	private List<SurveyOption> options;
	
	private Boolean required;
	
	private Boolean visible;
}
