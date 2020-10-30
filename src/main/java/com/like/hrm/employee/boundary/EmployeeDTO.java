package com.like.hrm.employee.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.like.hrm.employee.domain.model.SchoolCareer;
import com.like.hrm.employee.domain.model.StatusChangeHistory;
import com.like.hrm.employee.domain.model.DeptChangeHistory;
import com.like.hrm.employee.domain.model.Employee;
import com.like.hrm.employee.domain.model.Family;
import com.like.hrm.employee.domain.model.JobChangeHistory;
import com.like.hrm.employee.domain.model.License;
import com.like.hrm.employee.domain.model.QEmployee;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class EmployeeDTO {
		
	@Slf4j
	@Data
	@JsonInclude(Include.NON_EMPTY)	
	public static class SearchEmployee implements Serializable {			
		
		private static final long serialVersionUID = -3725100691674283297L;

		private final QEmployee qEmployee = QEmployee.employee;		

		LocalDate referenceDate;
		
		String id;
		
		String name;
		
		String deptType;
		
		String deptCode;
		
		List<String> deptCodeList;
					
		String deptName;
		
		String jobType;
		
		String jobCode;
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			log.info(this.deptType + " : "+ this.deptCodeList);
			
			builder				
				.and(likeId(this.id))
				.and(likeName(this.name))
				.and(eqDeptCode(this.deptType, this.deptCode))
				.and(inDeptCodeList(this.deptType, this.deptCodeList))
				.and(likeDeptName(this.deptName))
				.and(eqReferenceDate(this.referenceDate));				
			
			return builder;
		}
		
		private BooleanExpression likeId(String id) {
			if (StringUtils.isEmpty(id)) {
				return null;
			}
			
			return qEmployee.id.like("%"+id+"%");
		}
		
		private BooleanExpression likeName(String name) {
			if (StringUtils.isEmpty(name)) {
				return null;
			}
			
			return qEmployee.name.like("%"+name+"%");
		}
		
		private BooleanExpression eqDeptCode(String deptType, String deptCodeL) {
			if (StringUtils.isEmpty(deptCode)) {
				return null;
			}
					
			return qEmployee.equalDeptCode(deptType, deptCode);
		}
		
		private BooleanExpression inDeptCodeList(String deptType, List<String> deptCodeList) {
			if (deptCodeList == null) {
				return null;
			}
					
			return qEmployee.inDeptCode(deptType, deptCodeList);
		}
		
		private BooleanExpression likeDeptName(String deptName) {
			if (StringUtils.isEmpty(deptName)) {
				return null;
			}
			
			return qEmployee.likeDeptName("%"+deptName+"%", LocalDate.now());
		}
		
		private BooleanExpression eqReferenceDate(LocalDate date) {
			if (date == null)
				return null;
			
			return qEmployee.referenceDate(date);
		}
					
		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class NewEmployee implements Serializable {
		
		private static final long serialVersionUID = 5189496256963058913L;	
				
		@NotEmpty
		private String name;

		private String nameEng;
		
		private String nameChi;
				
		@NotEmpty
		private String residentRegistrationNumber;	
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class ResponseEmployee implements Serializable {
		
		private String id;	
		
		private String name;
		
		private String nameEng;
		
		private String nameChi;
		
		private String legalName;
					
		private String residentRegistrationNumber;
		
		private String gender;
		
		private LocalDate birthday;				
		
		private String imagePath;
		
		private Set<NewDept> deptHistory;
		
		private Set<NewJob> jobHistory;
		
		private Set<NewStatus> statusHistory;
		
		public static ResponseEmployee convert(Employee entity) {
			Set<NewDept> deptHistory = entity.getDeptHistory().getDeptChangeHistory()
											 .stream()
											 	.map(e -> NewDept.convert(e))
											 	.collect(Collectors.toSet());
			
			Set<NewJob> jobHistory = entity.getJobHistory().getJobChangeHistory()
										   .stream()
										   	.map(e -> NewJob.convert(e))
										   	.collect(Collectors.toSet());
				
			Set<NewStatus> statusHistory = entity.getStatusHistory().getStatusChangeHistory()
												 .stream()
													 .map(e -> NewStatus.convert(e))
													 .collect(Collectors.toSet());												 												 	
			
			return ResponseEmployee.builder()
								   .id(entity.getId())
								   .name(entity.getName())
								   .nameEng(entity.getNameEng())
								   .nameChi(entity.getNameChi())
								   .residentRegistrationNumber(entity.getResidentRegistrationNumber())
								   .gender(entity.getGender())
								   .birthday(entity.getBirthday())
								   .imagePath(entity.getImagePath())
								   .deptHistory(deptHistory)
								   .jobHistory(jobHistory)
								   .statusHistory(statusHistory)
								   .build();
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class SaveEmployee implements Serializable {
								
		private static final long serialVersionUID = -3475382902805357777L;

		@NotEmpty
		private String id;
				
		private String name;

		private String nameEng;
		
		private String nameChi;			
						
		private String gender;	
		
		private LocalDate birthday;
		
		public void modifyEntity(Employee entity) {
			entity.modifyEntity(this.name
					 		   ,this.nameEng
					 		   ,this.nameChi
					 		   ,this.gender
					 		   ,this.birthday);
		}
	}
		
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class NewDept implements Serializable {
				
		private static final long serialVersionUID = 8984690778056785945L;
		
		private String employeeId;
		
		private Long id;
		
		private String deptType;
						
		private String deptCode;
				
		private String deptName;
		
		private LocalDate fromDate;
					
		private LocalDate toDate;
		
		public static NewDept convert(DeptChangeHistory entity) {
			return NewDept.builder()
						  .employeeId(entity.getEmployee().getId())
						  .id(entity.getId())
						  .deptType(entity.getDeptType())
						  .deptCode(entity.getDeptCode())
						  .deptName(entity.getDeptName())
						  .fromDate(entity.getPeriod().getFrom())
						  .toDate(entity.getPeriod().getTo())
						  .build();
		}
	}
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class NewJob implements Serializable {					
		
		private static final long serialVersionUID = -5137257776558074803L;

		@NotEmpty
		private String employeeId;
		
		@NotEmpty
		private String jobType;
				
		@NotEmpty
		private String jobCode;
				
		@NotEmpty
		private LocalDate fromDate;
			
		@NotEmpty
		private LocalDate toDate;
		
		public static NewJob convert(JobChangeHistory entity) {
			return NewJob.builder()
						 .employeeId(entity.getEmployee().getId())
						 .jobType(entity.getJobType())
						 .jobCode(entity.getJobCode())
						 .fromDate(entity.getPeriod().getFrom())
						 .toDate(entity.getPeriod().getTo())
						 .build();			
		}
		
	}
	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class NewStatus implements Serializable {					

		private static final long serialVersionUID = -129845094404797994L;

		@NotEmpty
		private String employeeId;
		
		@NotEmpty
		private String appointmentCode;
				
		@NotEmpty
		private String statusCode;
				
		@NotEmpty
		private LocalDate fromDate;
			
		@NotEmpty
		private LocalDate toDate;
		
		public static NewStatus convert(StatusChangeHistory entity) {
			return NewStatus.builder()
							.employeeId(entity.getEmployee().getId())
							.appointmentCode(entity.getAppointmentCode())
							.statusCode(entity.getStatusCode())
							.fromDate(entity.getPeriod().getFrom())
							.toDate(entity.getPeriod().getTo())
							.build();
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SaveEducation implements Serializable {					

		private static final long serialVersionUID = -8768170007000992707L;

		@NotEmpty
		private String employeeId;
		
		@Nullable
		private Long educationId;
		
		@NotEmpty
		private String schoolCareerType;
				
		@NotEmpty
		private String schoolCode;
				
		@Nullable
		private String comment;
		
		public SchoolCareer newEntity(Employee employee) {
			return new SchoolCareer(employee
								,this.schoolCareerType
								,this.schoolCode
								,this.comment);
		}
		
		public void modifyEnity(SchoolCareer entity) {
			entity.modifyEntity(schoolCareerType
							   ,schoolCode
							   ,comment);	
		}	
		
		public static SaveEducation convert(SchoolCareer entity) {
			return SaveEducation.builder()
								.employeeId(entity.getEmployee().getId())
								.educationId(entity.getId())
								.schoolCareerType(entity.getSchoolCareerType())
								.schoolCode(entity.getSchoolCode())
								.comment(entity.getComment())
								.build();
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SaveLicense implements Serializable {						

		private static final long serialVersionUID = -4765555653271244793L;

		@NotEmpty
		private String employeeId;
		
		@Nullable
		private Long licenseId;
		
		@NotEmpty
		private String licenseType;
				
		@NotEmpty
		private String licenseCode;
				
		@Nullable
		private String comment;
		
		public License newEntity(Employee employee) {
			return new License(employee
							  ,this.licenseType
							  ,this.licenseCode
							  ,this.comment);
		}
		
		public void modifyEntity(License entity) {
			entity.modifyEntity(licenseType
							   ,licenseCode
							   ,comment);	
		}	
		
		public static SaveLicense convert(License entity)  {
			return SaveLicense.builder()
							  .employeeId(entity.getEmployee().getId())
							  .licenseId(entity.getLicenseId())
							  .licenseType(entity.getLicenseType())
							  .licenseCode(entity.getLicenseCode())
							  .comment(entity.getComment())
							  .build();
		}
	}	
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class SaveFamily implements Serializable {
		
		@NotEmpty
		private String employeeId;
		
		@Nullable
		private Long id;
		
		private String name;
				
		private String residentRegistrationNumber;
				
		private String relation;
				
		private String occupation;
				
		private String schoolCareerType;
				
		private String comment;
		
		public Family newEntity(Employee employee) {
			return new Family(employee
							 ,name
							 ,residentRegistrationNumber
							 ,relation
							 ,occupation
							 ,schoolCareerType
							 ,comment);					
		}
		
		public void modifyEntity(Family entity) {
			entity.modifyEntity(name
							   ,residentRegistrationNumber
							   ,relation
							   ,occupation
							   ,schoolCareerType
							   ,comment);
		}
		
		public static SaveFamily convert(Family entity) {
			return SaveFamily.builder()
							 .employeeId(entity.getEmployee().getId())
							 .id(entity.getId())
							 .name(entity.getResidentRegistrationNumber())
							 .relation(entity.getRelation())
							 .occupation(entity.getOccupation())
							 .schoolCareerType(entity.getSchoolCareerType())
							 .comment(entity.getComment())
							 .build();
		}
	}
	
}
