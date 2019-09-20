package com.like.hrm.appointment.boundary;

import java.io.Serializable;
import java.time.LocalDate;

import com.like.hrm.appointment.domain.model.Ledger;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LedgerDTO {
	
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCode implements Serializable {
												
		private static final long serialVersionUID = -339266416839829125L;

		private String LedgerId;
			
		private String appointmentType;					
								
		private LocalDate registrationDate;
						
		private String comment;
		
		public Ledger newEntity() {
			return new Ledger(this.LedgerId
							  ,this.appointmentType
							  ,this.registrationDate							  
							  ,this.comment);
		}
		
		/*public AppointmentCode changeInfo(AppointmentCode entity) {
			entity.ChangeInfo(this.codeName
							 ,this.useYn
							 ,this.endDateYn
							 ,this.sequence
							 ,this.comment);
			
			return entity;
		}*/
	}
}
