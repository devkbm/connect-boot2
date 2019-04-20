package com.like.team.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.like.dept.dto.DeptDTO;
import com.like.dept.dto.DeptDTO.DeptSave;
import com.like.team.domain.model.QTeam;
import com.querydsl.core.BooleanBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TeamDTO {
	
	@Data
	public static class SearchCondition implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QTeam qTeam = QTeam.team;			
				
		Long teamId;
		
		String teamName;						
		
		public BooleanBuilder getCondition() {
			BooleanBuilder builder = new BooleanBuilder();
								
			if (this.teamId != null) {
				builder.and(qTeam.teamId.eq(this.teamId));
			}			
			
			if (StringUtils.hasText(this.teamName)) {
				builder.and(qTeam.teamName.like("%"+this.teamName+"%"));
			}			
			
			return builder;
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class TeamSave implements Serializable {
				
		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		Long teamId;

		String teamName;
		
		List<String> memberList;
	}
		
}
