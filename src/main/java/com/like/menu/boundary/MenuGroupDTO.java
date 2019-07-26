package com.like.menu.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.menu.domain.model.QMenuGroup;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

public class MenuGroupDTO {

	@Data
	public static class MenuGroupSave implements Serializable {
		
		private static final long serialVersionUID = 3108072896516171536L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotEmpty	
		private String menuGroupCode;
		
		@NotEmpty	
		private String menuGroupName;
				
		private String description;		
			
	}
}
