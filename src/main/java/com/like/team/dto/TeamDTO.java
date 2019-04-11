package com.like.team.dto;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.like.team.domain.model.QTeam;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

public class TeamDTO {
	
	@Data
	public static class SearchCondition implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QTeam qTeam = QTeam.team;			
				
		String teamName;						
		
		public BooleanBuilder getCondition() {
			BooleanBuilder builder = new BooleanBuilder();
								
			if (StringUtils.hasText(this.teamName)) {
				builder.and(qTeam.teamName.like("%"+this.teamName+"%"));
			}			
			
			return builder;
		}
	}
	
		
}
