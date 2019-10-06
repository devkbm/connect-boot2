package com.like.workschedule.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.workschedule.domain.model.QWorkGroup;
import com.like.workschedule.domain.model.WorkGroup;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class WorkDTO {
	
	public static WorkDTO.SaveWorkGroup convertDTO(WorkGroup entity) {
		WorkDTO.SaveWorkGroup dto = SaveWorkGroup.builder()
												 .workGroupId(entity.getId())
												 .workGroupName(entity.getName())
												 .color(entity.getColor())
												 .memberList(entity.getMemberList().stream().map( r -> r.getUser().getUserId()).collect(Collectors.toList()))
												 .build();
		
		return dto;
	}
	
	@Data
	public static class SearchWorkGroup implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QWorkGroup qWorkGroup = QWorkGroup.workGroup;
						
		String name;			
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder.and(likeGroupName(this.name));			
			
			return builder;
		}
		
		private BooleanExpression likeGroupName(String name) {
			if (StringUtils.isEmpty(name)) {
				return null;
			}
			
			return qWorkGroup.name.like("%"+this.name+"%");
		}
	}	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SaveWorkGroup implements Serializable {
				
		private static final long serialVersionUID = 8230052719254860669L;

		LocalDateTime createdDt;	 
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@Nullable
		Long workGroupId;
				
		@NotEmpty
		String workGroupName;		
		
		String color;
		
		List<String> memberList;
		
		public WorkGroup newWorkGroup() {
			return new WorkGroup(this.workGroupName, this.color);
		}
		
		public void modifyWorkGroup(WorkGroup workGroup) {
			workGroup.modifyEntity(this.workGroupName, color);
		}
	}
		
	
}
