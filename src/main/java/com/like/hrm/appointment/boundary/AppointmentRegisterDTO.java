package com.like.hrm.appointment.boundary;

import java.io.Serializable;
import java.time.LocalDate;


import org.springframework.util.StringUtils;

import com.like.hrm.appointment.domain.model.AppointmentRegister;
import com.like.hrm.appointment.domain.model.QAppointmentRegister;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AppointmentRegisterDTO {		
	
	@Data
	public static class SearchAppointmentRegister implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QAppointmentRegister qType = QAppointmentRegister.appointmentRegister;
				
		private String ledgerId;
									
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeLedgerId(ledgerId));
						
			return builder;
		}
		
		private BooleanExpression likeLedgerId(String ledgerId) {
			if (StringUtils.isEmpty(ledgerId)) {
				return null;
			}
			
			return qType.id.like(ledgerId);
		}
				
	}
	
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveAppointmentRegister implements Serializable {
												
		private static final long serialVersionUID = -339266416839829125L;

		private String ledgerId;
			
		private String appointmentType;					
								
		private LocalDate registrationDate;
						
		private String comment;
		
		public AppointmentRegister newEntity() {
			return new AppointmentRegister(this.ledgerId
										  ,this.appointmentType
										  ,this.registrationDate							  
										  ,this.comment);
		}
		
		public AppointmentRegister modifyEntity(AppointmentRegister entity) {
			entity.changeInfo(this.appointmentType												 
							 ,this.comment);
			
			return entity;
		}
	}		
	
}
