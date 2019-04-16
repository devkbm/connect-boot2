package com.like.team.domain.model;

import java.util.stream.Collectors;

import com.like.team.dto.TeamDTO;

public class TeamDTOAssembler {	
		
	public static TeamDTO.TeamSave convertDTO(Team entity) {					
		
		if (entity == null)
			return null;
		
		TeamDTO.TeamSave dto = TeamDTO.TeamSave.builder()
								.createdDt(entity.getCreatedDt())
								.createdBy(entity.getCreatedBy())
								.modifiedDt(entity.getModifiedDt())
								.modifiedBy(entity.getModifiedBy())
								.teamId(entity.getTeamId())
								.teamName(entity.teamName)
								.memberList(entity.getMemberList().stream()
																  .map(r -> r.getUserId())
																  .collect(Collectors.toList()))	
								.build();		
		return dto;
	}
			
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a가 NULL일 경우 b, 이외에는 a 리턴
	 */
	private static <T>T nvl(T a, T b) {		
		return a == null ? b : a;
	}
		
			
}
