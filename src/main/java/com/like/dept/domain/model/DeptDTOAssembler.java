package com.like.dept.domain.model;

import com.like.dept.boundary.DeptDTO;
import com.like.dept.boundary.DeptDTO.DeptSave;
import com.like.dept.domain.repository.DeptRepository;

public class DeptDTOAssembler {	
		
	public static Dept toEntity(DeptDTO.DeptSave dto,DeptRepository repository) {
		Dept dept = repository.getDept(dto.getDeptCode());
		Dept parentDept = null;
		
		if (dto.getParentDeptCode() != null) {
			parentDept = repository.getDept(dto.getParentDeptCode());
		}			
		
		if (dept == null) {
			dept = Dept.builder()
					   .deptCode(dto.getDeptCode())
					   .deptNameKorean(dto.getDeptNameKorean())
					   .deptAbbreviationKorean(dto.getDeptAbbreviationKorean())
					   .deptNameEnglish(dto.getDeptNameEnglish())
					   .deptAbbreviationEnglish(dto.getDeptAbbreviationEnglish())
					   .fromDate(dto.getFromDate())
					   .toDate(dto.getToDate())
					   .seq(dto.getSeq())
					   .comment(dto.getComment())
					   .parentDept(parentDept)
					   .build();
		} else {
			dept.deptNameKorean 		= nvl(dto.getDeptNameKorean(), 				dept.deptNameKorean);		
			dept.deptAbbreviationKorean = nvl(dto.getDeptAbbreviationKorean(), 		dept.deptAbbreviationKorean);
			dept.deptNameEnglish 		= nvl(dto.getDeptNameEnglish(), 			dept.deptNameEnglish);		
			dept.deptAbbreviationEnglish = nvl(dto.getDeptAbbreviationEnglish(), 	dept.deptAbbreviationEnglish);
			
			dept.toDate					= nvl(dto.getToDate(),		dept.toDate);
			dept.seq					= nvl(dto.getSeq(),			dept.seq);
			dept.comment				= nvl(dto.getComment(),		dept.comment);
			dept.parentDept				= parentDept;
		}
		
		return dept;
	}
	
	public static Dept createEntity(DeptDTO.DeptSave dto, Dept parentDept) {
		if (dto.getDeptCode() == null) {
			new IllegalArgumentException("부서코드가 없습니다.");
		}
		
		return Dept.builder()
				.deptCode(dto.getDeptCode())
				.deptNameKorean(dto.getDeptNameKorean())
				.deptAbbreviationKorean(dto.getDeptAbbreviationKorean())
				.deptNameEnglish(dto.getDeptNameEnglish())
				.deptAbbreviationEnglish(dto.getDeptAbbreviationEnglish())				
				.fromDate(dto.getFromDate())
				.toDate(dto.getToDate())
				.seq(dto.getSeq())
				.comment(dto.getComment())
				.parentDept(parentDept)
				.build();
	}
	
	public static Dept mergeEntity(Dept dept, DeptDTO.DeptSave dto, Dept parentDept) {
		
		if (dept == null)
			throw new IllegalArgumentException("Dept 엔티티가 존재하지 않습니다.");
		
		dept.deptNameKorean 		= nvl(dto.getDeptNameKorean(), 				dept.deptNameKorean);		
		dept.deptAbbreviationKorean = nvl(dto.getDeptAbbreviationKorean(), 		dept.deptAbbreviationKorean);
		dept.deptNameEnglish 		= nvl(dto.getDeptNameEnglish(), 			dept.deptNameEnglish);		
		dept.deptAbbreviationEnglish = nvl(dto.getDeptAbbreviationEnglish(), 	dept.deptAbbreviationEnglish);
		
		dept.toDate					= nvl(dto.getToDate(),		dept.toDate);
		dept.seq					= nvl(dto.getSeq(),			dept.seq);
		dept.comment				= nvl(dto.getComment(),		dept.comment);
		
		if (parentDept != null) {
			dept.parentDept = parentDept; 
		}
		
		return dept;
	}	
	
	public static DeptDTO.DeptSave convertDTO(Dept entity) {							
		
		Dept parent = entity.getParentDept();							
		
		DeptSave dto = DeptSave.builder()
								.createdDt(entity.getCreatedDt())
								.createdBy(entity.getCreatedBy())
								.modifiedDt(entity.getModifiedDt())
								.modifiedBy(entity.getModifiedBy())
								.deptCode(entity.deptCode)
								.parentDeptCode(parent == null ? null : parent.getDeptCode())								
								.deptNameKorean(entity.deptNameKorean)
								.deptAbbreviationKorean(entity.deptAbbreviationKorean)
								.deptNameEnglish(entity.deptNameEnglish)
								.deptAbbreviationEnglish(entity.deptAbbreviationEnglish)
								.fromDate(entity.fromDate)
								.toDate(entity.toDate)
								.seq(entity.seq)
								.comment(entity.comment)
								.build();		
		return dto;		
	}
			
	/**
	 * 
	 * @param a
	 * @param b
	 * @return a가 NULL일 경우 b, 이외에는 a 리턴
	 */
	private static <T>T nvl(T a, T b) {		
		return a == null ? b : a;
	}
		
			
}
