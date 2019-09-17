package com.like.hrm.appointment.boundary;

import java.io.Serializable;

import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AppointmentCodeDTO {

	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCode implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;
					
		private String code;
			
		private String codeName;					
								
		private int sequence;
			
		private boolean useYn;
		
		private boolean endDateYn;
		
		private String comment;
		
		public AppointmentCode newAppointmentCode() {
			return new AppointmentCode(this.code
									  ,this.codeName
									  ,this.useYn
									  ,this.endDateYn
									  ,this.sequence
									  ,this.comment									  
									  ,null);
		}
		
		public AppointmentCode changeInfo(AppointmentCode entity) {
			entity.ChangeInfo(this.codeName
							 ,this.useYn
							 ,this.endDateYn
							 ,this.sequence
							 ,this.comment);
			
			return entity;
		}
	}
	
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCodeDetail implements Serializable {					
					
		private static final long serialVersionUID = 309168416341042059L;

		private String code;
							
		private String changeType;					
		
		private String changeTypeDetail;
								
		private int sequence;	
		
		public AppointmentCodeDetail newAppointmentCodeDetail(AppointmentCode code) {
						
			AppointmentCodeDetail detail = new AppointmentCodeDetail(code
																	,this.changeType
																	,this.changeTypeDetail
																	,this.sequence);
			return detail;
		}
		
		public String getDetailId() {
			return this.code + this.changeType + this.changeTypeDetail;
		}
	}
}
