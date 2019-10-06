package com.like.dept.boundary;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.dept.domain.model.Dept;
import com.like.dept.domain.model.QDept;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

public class DeptDTO {	
	
	@Data
	public static class SearchDept implements Serializable {
		
		private static final long serialVersionUID = -4777670465777456711L;

		private final QDept qDept = QDept.dept;
		
		String deptCode;
				
		String deptName;
					
		Boolean isEnabled;
		
		public BooleanBuilder getCondition() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeDeptCode(this.deptCode))
				.and(likeDeptName(this.deptCode));
														
			return builder;
		}
		
		private BooleanExpression likeDeptCode(String deptCode) {
			if (StringUtils.isEmpty(deptCode)) {
				return null;
			}
			
			return qDept.deptCode.like("%"+deptCode+"%");
		}
		
		private BooleanExpression likeDeptName(String deptName) {
			if (StringUtils.isEmpty(deptCode)) {
				return null;
			}
			
			return qDept.deptNameKorean.like("%"+deptName+"%");
		}
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder	
	public static class SaveDept implements Serializable {
		
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
		
		public Dept newDept(Dept parentDept) {
			if (this.deptCode == null) {
				new IllegalArgumentException("부서코드가 없습니다.");
			}
			
			return Dept.builder()
					   .deptCode(this.deptCode)
					   .deptNameKorean(this.deptNameKorean)
					   .deptAbbreviationKorean(this.deptAbbreviationKorean)
					   .deptNameEnglish(this.deptNameEnglish)
					   .deptAbbreviationEnglish(this.deptAbbreviationEnglish)				
					   .fromDate(this.fromDate)
					   .toDate(this.toDate)
					   .seq(this.seq)
					   .comment(this.comment)
					   .parentDept(parentDept)
					   .build();
		}
		
		public void modifyDept(Dept dept, Dept parentDept) {
			dept.modifyEntity(deptNameKorean
							 ,deptAbbreviationKorean
							 ,deptNameEnglish
							 ,deptAbbreviationEnglish
							 ,fromDate
							 ,toDate
							 ,seq
							 ,comment
							 ,parentDept);
		}
				
	}
	
	public static DeptDTO.SaveDept convertDTO(Dept entity) {							
		
		Dept parent = entity.getParentDept();							
		
		SaveDept dto = SaveDept.builder()
								.createdDt(entity.getCreatedDt())
								.createdBy(entity.getCreatedBy())
								.modifiedDt(entity.getModifiedDt())
								.modifiedBy(entity.getModifiedBy())
								.deptCode(entity.getDeptCode())
								.parentDeptCode(parent == null ? null : parent.getDeptCode())								
								.deptNameKorean(entity.getDeptNameKorean())
								.deptAbbreviationKorean(entity.getDeptAbbreviationKorean())
								.deptNameEnglish(entity.getDeptNameEnglish())
								.deptAbbreviationEnglish(entity.getDeptAbbreviationEnglish())
								.fromDate(entity.getFromDate())
								.toDate(entity.getToDate())
								.seq(entity.getSeq())
								.comment(entity.getComment())
								.build();		
		return dto;		
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder	
	public static class DeptHierarchy implements Serializable {		
		
		private static final long serialVersionUID = 1768165409310985060L;

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
		
		@Singular
		List<DeptDTO.DeptHierarchy> children;
		
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
