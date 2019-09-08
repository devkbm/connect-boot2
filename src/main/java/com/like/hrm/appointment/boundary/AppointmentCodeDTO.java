package com.like.hrm.appointment.boundary;

import java.io.Serializable;

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
								
		private int sequence;
			
		private boolean useYn;
		
		private String cmt;
	}
	
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class CodeDetailSave implements Serializable {					
					
		private static final long serialVersionUID = 309168416341042059L;

		private String code;
			
		private String changeType;					
		
		private String changeTypeDetail;
								
		private int sequence;				
	}
}
