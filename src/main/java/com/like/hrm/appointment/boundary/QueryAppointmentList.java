package com.like.hrm.appointment.boundary;

import java.io.Serializable;
import java.time.LocalDate;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QueryAppointmentList implements Serializable {
	private static final long serialVersionUID = -999656657224379274L;

	private String ledgerId;
	
	private Long listId;
			
	private Long sequence;
				
	private String empId;	
	
	private String empName;
						
	private String appointmentCode;
	
	private String appointmentCodeName;
	
	private LocalDate appointmentFromDate;
					
	private LocalDate appointmentToDate;
	
	private Boolean finishYn;

	/**
	 * @param ledgerId
	 * @param listId
	 * @param sequence
	 * @param empId
	 * @param empName
	 * @param appointmentCode
	 * @param appointmentCodeName
	 * @param appointmentFromDate
	 * @param appointmentToDate
	 * @param finishYn
	 */
	@QueryProjection
	public QueryAppointmentList(String ledgerId
			,Long listId
			,Long sequence
			,String empId
			,String empName
			,String appointmentCode
			,String appointmentCodeName
			,LocalDate appointmentFromDate
			,LocalDate appointmentToDate
			,Boolean finishYn) {
		this.ledgerId = ledgerId;
		this.listId = listId;
		this.sequence = sequence;
		this.empId = empId;
		this.empName = empName;
		this.appointmentCode = appointmentCode;
		this.appointmentCodeName = appointmentCodeName;
		this.appointmentFromDate = appointmentFromDate;
		this.appointmentToDate = appointmentToDate;
		this.finishYn = finishYn;
	}
}
