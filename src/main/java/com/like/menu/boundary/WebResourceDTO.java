package com.like.menu.boundary;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

public class WebResourceDTO {
	
	@Data
	public static class ResourceSave implements Serializable {
				
		private static final long serialVersionUID = -1400051159309726788L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotEmpty
		String resourceCode;
			
		@NotEmpty
		String resourceName; 
				
		String resourceType;
				
		@NotEmpty
		String url;
			
		String description;		
		
	}
}
