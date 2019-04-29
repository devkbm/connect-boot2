package com.like.dept.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.dept.domain.model.QDept;
import com.querydsl.core.BooleanBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class DeptDTO {
	
	@Data
	public static class SearchCondition implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QDept qDept = QDept.dept;
		
		String deptCode;
				
		String deptName;
					
		Boolean isEnabled;
		
		public BooleanBuilder getCondition() {
			BooleanBuilder builder = new BooleanBuilder();
			
			if (StringUtils.hasText(this.deptCode)) {
				builder.and(qDept.deptCode.like("%"+this.deptCode+"%"));
			}
			
			if (StringUtils.hasText(this.deptName)) {
				builder.and(qDept.deptAbbreviationKorean.like("%"+this.deptName+"%"));
			}			
			
			return builder;
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder	
	public static class DeptSave implements Serializable {
		
		private static final long serialVersionUID = -670038546212531439L;

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		String parentDeptCode;
		
		@NotEmpty(message="부서코드는 필수 입력 사항입니다.")
		String deptCode;		
		
		@NotEmpty(message="부서명(한글)은 필수 입력 사항입니다.")
		String deptNameKorean;		
		
		String deptAbbreviationKorean;
		
		String deptNameEnglish;
		
		String deptAbbreviationEnglish;
							
		LocalDate fromDate;
				
		LocalDate toDate;
		
		Integer seq;
		
		String comment;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder	
	public static class DeptHierarchy implements Serializable {		

		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		String parentDeptCode;
				
		String deptCode;		
				
		String deptNameKorean;		
		
		String deptAbbreviationKorean;
		
		String deptNameEnglish;
		
		String deptAbbreviationEnglish;
							
		LocalDate fromDate;
				
		LocalDate toDate;
		
		Integer seq;
		
		String comment;
		
		List<DeptDTO.DeptHierarchy> children = new ArrayList<>();
		
		/**
		 * NzTreeNode property 
		 */
		String title;
		
		String key;
				
		@JsonProperty(value="isLeaf") 
		boolean isLeaf;

		public DeptHierarchy(LocalDateTime createdDt, String createdBy, LocalDateTime modifiedDt, String modifiedBy,
				String parentDeptCode, String deptCode, String deptNameKorean, String deptAbbreviationKorean,
				String deptNameEnglish, String deptAbbreviationEnglish, LocalDate fromDate, LocalDate toDate,
				Integer seq, String comment) {
			super();
			this.createdDt = createdDt;
			this.createdBy = createdBy;
			this.modifiedDt = modifiedDt;
			this.modifiedBy = modifiedBy;
			this.parentDeptCode = parentDeptCode;
			this.deptCode = deptCode;
			this.deptNameKorean = deptNameKorean;
			this.deptAbbreviationKorean = deptAbbreviationKorean;
			this.deptNameEnglish = deptNameEnglish;
			this.deptAbbreviationEnglish = deptAbbreviationEnglish;
			this.fromDate = fromDate;
			this.toDate = toDate;
			this.seq = seq;
			this.comment = comment;
			
			this.title 	= this.deptNameKorean;
			this.key 	= this.deptCode;			
		}

		
		
		
	}
	
}
