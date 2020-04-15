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
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = {"surveyForm"})
@ToString(callSuper=true, includeFieldNames=true)
@Entity
@Table(name = "GRWSURVEYFORM")
@EntityListeners(AuditingEntityListener.class)
public class SurveyItem extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -11605126548714991L;

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
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
		
	@ManyToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name="form_id", nullable=false, updatable=false)
	private SurveyForm surveyForm;
	
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
