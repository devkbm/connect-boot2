package com.like.hrm.appointment.dto;

import java.io.Serializable;
import java.time.LocalDate;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AppointmentCodeDTO {

	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class CodeSave implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;
		
			
		private String code;
			
		private String codeName;					
			
		private LocalDate fromDate;
			
		private LocalDate toDate;
			
		private int seq;
			
		private boolean useYn;
		
		private String cmt;
									
	}
}
