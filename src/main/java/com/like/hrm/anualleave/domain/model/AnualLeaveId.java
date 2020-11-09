package com.like.hrm.anualleave.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class AnualLeaveId implements Serializable {
	
	private static final long serialVersionUID = 7192154823642621593L;

	// 귀속년도
	@Column(name="YYYY")
	private Integer yyyy;
		
	// 사원번호
	@Column(name="EMP_ID")
	private String empId;
}
