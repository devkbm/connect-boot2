package com.like.term.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.board.domain.model.QArticle;
import com.like.term.domain.model.QTermDictionary;
import com.querydsl.core.BooleanBuilder;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class TermDTO {

	@Data
	public static class QueryCondition implements Serializable {
		
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
	
	
}
