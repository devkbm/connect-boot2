package com.like.hrm.employee.boundary;

import java.io.Serializable;
import java.time.LocalDate;

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
		
		private String name;
					
		private String residentRegistrationNumber;
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class NewDept implements Serializable {
				
		private static final long serialVersionUID = 8984690778056785945L;

		private String id;
		
		private String deptType;
				
		private String deptCode;
				
		private LocalDate fromDate;
			
		private LocalDate toDate;
		
	}
	
}
