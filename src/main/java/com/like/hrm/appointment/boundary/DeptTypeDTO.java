package com.like.hrm.appointment.boundary;

import java.io.Serializable;

import com.like.hrm.appointment.domain.model.DeptType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DeptTypeDTO {

	@Data	
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCode implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;
					
		private String id;
		
		private String code;
			
		private String codeName;					
			
		private boolean useYn;
		
		private Integer sequence;
		
		private String comment;
				
		public DeptType newDeptType() {
			return new DeptType(this.code
					   		   ,this.codeName
					   		   ,this.useYn
					   		   ,this.sequence
					   		   ,this.comment);
		}
		
		public DeptType changeInfo(DeptType entity) {
			entity.changeInfo(this.codeName
							 ,this.useYn
							 ,this.sequence
							 ,this.comment);
			return entity;
		}
		
		public static SaveCode convert(DeptType entity) {
			/*
			return SaveCode.builder()
						   .id(entity.getId())
						   .code(entity.getCode())
						   .codeName(entity.getCodeName())
						   .useYn(entity.isUseYn())
						   .sequence(entity.getSequence())
						   .code(entity.getComment())
						   .build();						   
			*/
			
			return new SaveCode(entity.getId()
							   ,entity.getCode()
							   ,entity.getCodeName()
							   ,entity.isUseYn()
							   ,entity.getSequence()
							   ,entity.getComment());
			
		}
		
		
	}
}
