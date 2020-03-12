package com.like.hrm.appointment.boundary;

import java.io.Serializable;

import com.like.hrm.appointment.domain.model.JobType;
import com.like.hrm.appointment.domain.model.TypeDetailCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TypeDetailCodeDTO {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCode implements Serializable {					
				
		private static final long serialVersionUID = -4493967354550706137L;

		private String id;
		
		private String typeId;
		
		private String code;
		
		private String codeName;					
			
		private boolean useYn;
		
		private Integer sequence;
		
		private String comment;
		
		public TypeDetailCode newTypeDetailCode() {
			return new TypeDetailCode(this.typeId
									 ,this.code
							  		 ,this.codeName
							  		 ,this.useYn
							   		 ,this.sequence
							   		 ,this.comment);
		}
			
		public TypeDetailCode changeInfo(TypeDetailCode entity) {
			entity.changeInfo(this.codeName
							 ,this.useYn
							 ,this.sequence
							 ,this.comment);
			return entity;
		}

		public static SaveCode convert(TypeDetailCode entity) {
			if (entity == null) return null;
			
			return new SaveCode(entity.getId()
							   ,entity.getTypeId() 
					           ,entity.getCode()
					           ,entity.getCodeName()
					           ,entity.isUseYn()
					           ,entity.getSequence()
					           ,entity.getComment());			
		}
	}
}
