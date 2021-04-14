package com.like.term.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.core.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>용어사전 엔티티<p/>
 * 
 */
@Getter
@ToString(callSuper=true, includeFieldNames=true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "COMTERM")
@EntityListeners(AuditingEntityListener.class)
public class TermDictionary extends AuditEntity {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_TERM")
	Long pkTerm;	
	
	@Column(name="DOMAIN_TYPE")
	String domain;
	
	@Column(name="TERM")
	String term;
		
	@Column(name="NAME_KOR")
	String nameKor;
	
	@Column(name="ABBR_KOR")
	String abbreviationKor;
	
	@Column(name="NAME_ENG")
	String nameEng;
		
	@Column(name="ABBR_ENG")
	String abbreviationEng;
	
	@Column(name="DESCRIPTION")
	String description;
	
	@Column(name="CMT")
	String comment;

	@Builder
	public TermDictionary(String domain, String term, String nameKor, String abbreviationKor, String nameEng,
			String abbreviationEng, String description, String comment) {		
		this.domain = domain;
		this.term = term;
		this.nameKor = nameKor;
		this.abbreviationKor = abbreviationKor;
		this.nameEng = nameEng;
		this.abbreviationEng = abbreviationEng;
		this.description = description;
		this.comment = comment;
	}	
	
	public void modifyEntity(String domain
							,String term
							,String nameKor
							,String abbreviationKor
							,String nameEng
							,String abbreviationEng
							,String description
							,String comment) {	
		this.domain = domain;
		this.term = term;
		this.nameKor = nameKor;
		this.abbreviationKor = abbreviationKor;
		this.nameEng = nameEng;
		this.abbreviationEng = abbreviationEng;
		this.description = description;
		this.comment = comment;
	}

	
	
}
