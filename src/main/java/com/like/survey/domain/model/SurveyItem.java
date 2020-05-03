package com.like.survey.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = {"surveyForm"})
@ToString(callSuper=true, includeFieldNames=true)
@Getter
@Entity
@Table(name = "GRWSURVEYITEM")
@EntityListeners(AuditingEntityListener.class)
public class SurveyItem extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -11605126548714991L;

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ITEM_ID")
	private Long itemId;
	
	/**
	 * Radio, Checkbox, Text
	 */
	@Column(name="ITEM_TYPE")
	private String itemType;
	
	@Column(name="LABEL")
	private String label;
	
	@Column(name="VALUE")
	private String value;
	
	@Column(name="REQUIRED_YN")
	private Boolean required;	
	
	@Column(name="CMT")
	private String comment;
	
	//private List<SurveyOption> options;
		
	@ManyToOne(optional = false)			
	@JoinColumn(name="form_id", nullable=false, updatable=false)
	private SurveyForm surveyForm;
	
	public SurveyItem(SurveyForm surveyForm
					 ,String itemType
					 ,String label
					 ,String value
					 ,Boolean required
					 ,String comment) {
		this.surveyForm = surveyForm;
		this.itemType = itemType;
		this.label = label;
		this.value = value;
		this.required = required;
		this.comment = comment;
	}
	
	
	public void modifyEntity(String itemType
							,String label
							,String value
							,boolean required
							,boolean visible) {
		this.itemType = itemType;
		this.label = label;
		this.value = value;
		this.required = required;		
	}
	/*
	public void addOption(SurveyOption option) {
		if (this.options == null) 
			this.options = new ArrayList<>();
		
		this.options.add(option);
	}
	
	public void removeOption(SurveyOption option) {
		this.options.remove(option);
	}
	*/
	
}
