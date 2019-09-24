package com.like.hrm.appointment.boundary;

import java.io.Serializable;

import com.like.hrm.appointment.domain.model.JobType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class JobTypeDTO {

	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCode implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;
		
		private String id;
		
		private String code;
		
		private String codeName;					
			
		private boolean useYn;
		
		private Integer sequence;
		
		private String comment;
		
		public JobType newJobType() {
			return new JobType(this.code
					  		  ,this.codeName
					  		  ,this.useYn
					   		  ,this.sequence
					   		  ,this.comment);
		}
			
		public JobType changeInfo(JobType entity) {
			entity.changeInfo(this.codeName
							 ,this.useYn
							 ,this.sequence
							 ,this.comment);
			return entity;
		}
	}
}
