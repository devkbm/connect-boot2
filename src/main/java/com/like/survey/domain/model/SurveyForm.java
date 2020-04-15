package com.like.survey.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper=true, includeFieldNames=true)
@Entity
@Table(name = "GRWSURVEYFORM")
@EntityListeners(AuditingEntityListener.class)
public class SurveyForm extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = -1743405102755393150L;

	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CMT")
	private String comment;
	
	@OneToMany(mappedBy = "surveyForm", cascade = CascadeType.ALL, orphanRemoval = true )
	private List<SurveyItem> items;
		
	public void modifyEntity(String title
							,String comment) {
		this.title = title;
		this.comment = comment;
	}
	
	public SurveyItem getItem(Long id) {
		return null;
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
