package com.like.hrm.employee.boundary;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EmployeeDTO {

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class NewEmployee implements Serializable {
		
		private static final long serialVersionUID = 5189496256963058913L;	
				
		@NotEmpty
		private String name;
				
		@NotEmpty
		private String residentRegistrationNumber;
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class NewDept implements Serializable {
				
		private static final long serialVersionUID = 8984690778056785945L;

		@NotEmpty
		private String employeeId;
		
		@NotEmpty
		private String deptType;
				
		@NotEmpty
		private String deptCode;
				
		@NotEmpty
		private LocalDate fromDate;
			
		@NotEmpty
		private LocalDate toDate;
		
	}
	
}
