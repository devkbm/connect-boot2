package com.like.term.boundary;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.term.domain.model.QTermDictionary;
import com.like.term.domain.model.TermDictionary;
import com.querydsl.core.BooleanBuilder;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TermDTO {

	@Data
	public static class SearchTerm implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QTermDictionary qTermDictionary = QTermDictionary.termDictionary;
						
		String domain;
		
		String term;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
									
			if (StringUtils.hasText(this.domain)) {
				builder.and(qTermDictionary.domain.like("%"+this.domain+"%"));
			}
			
			if (StringUtils.hasText(this.term)) {
				builder.and(qTermDictionary.term.like("%"+this.term+"%"));
			}
			
			return builder;
		}
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveTerm implements Serializable {
				
		private static final long serialVersionUID = 6104580538309815203L;

		Long pkTerm;	
				
		String domain;
				
		String term;
					
		String nameKor;
				
		String abbreviationKor;
				
		String nameEng;
					
		String abbreviationEng;
				
		String description;
				
		String comment;
		
		public TermDictionary newEntity() {
			return TermDictionary.builder()
								 .domain(domain)
								 .term(term)
								 .nameKor(nameKor)
								 .abbreviationKor(abbreviationKor)
								 .nameEng(nameEng)
								 .abbreviationEng(abbreviationEng)
								 .description(description)
								 .comment(comment)
								 .build();
		}
		
		public void modifyEntity(TermDictionary entity) {
			entity.modifyEntity(domain
					           ,term
					           ,nameKor
					           ,abbreviationKor
					           ,nameEng
					           ,abbreviationEng
					           ,description
					           ,comment);
			
		}
		
		public static SaveTerm convert(TermDictionary entity) {
			return SaveTerm.builder()
						   .pkTerm(entity.getPkTerm())
						   .domain(entity.getDomain())
						   .term(entity.getTerm())
						   .nameKor(entity.getNameKor())
						   .abbreviationKor(entity.getAbbreviationKor())
						   .nameEng(entity.getNameEng())
						   .abbreviationEng(entity.getAbbreviationEng())
						   .description(entity.getDescription())
						   .comment(entity.getComment())
						   .build();
		}
	}
}
