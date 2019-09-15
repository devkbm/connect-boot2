package com.like.hrm.appointment.boundary;

import java.io.Serializable;

import com.like.hrm.appointment.domain.model.DeptType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DeptTypeDTO {

	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCode implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;
					
		private String code;
			
		private String codeName;					
			
		private boolean useYn;
		
		private Integer sequence;
		
		private String comment;
				
		public DeptType newDeptType() {
			return new DeptType(this.code, this.codeName);
		}
	}
}
