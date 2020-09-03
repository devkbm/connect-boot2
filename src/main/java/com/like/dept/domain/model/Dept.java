package com.like.dept.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.like.common.domain.AuditEntity;
import com.like.common.vo.Period;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true, includeFieldNames = true)
@Builder
@Getter
@Entity
@Table(name = "comdept")
@EntityListeners(AuditingEntityListener.class)
public class Dept extends AuditEntity implements Serializable {

	private static final long serialVersionUID = -969524977226888898L;

	@Id
	@Column(name = "dept_cd", nullable = false)
	String deptCode;

	@Column(name = "dept_nm_kor")
	String deptNameKorean;

	@Column(name = "dept_abbr_kor")
	String deptAbbreviationKorean;

	@Column(name = "dept_nm_eng")
	String deptNameEnglish;

	@Column(name = "dept_abbr_eng")
	String deptAbbreviationEnglish;
	
	@Embedded
	Period period;
	
	@Builder.Default
	@Column(name="prt_seq")
	int seq = 0;
	
	@Column(name = "cmt")
	String comment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_dept_cd", nullable = true)
	Dept parentDept;

	public String getDeptCode() {
		return this.deptCode;
	}
	
	public Dept getParentDept() {
		return parentDept;
	}

	/**
	 * @param deptNameKorean
	 * @param deptAbbreviationKorean
	 * @param deptNameEnglish
	 * @param deptAbbreviationEnglish
	 * @param fromDate
	 * @param toDate
	 * @param seq
	 * @param comment
	 * @param parentDept
	 */
	public void modifyEntity(String deptNameKorean
							,String deptAbbreviationKorean
							,String deptNameEnglish
							,String deptAbbreviationEnglish
							,Period period
							,int seq
							,String comment
							,Dept parentDept) {
		this.deptNameKorean = deptNameKorean;
		this.deptAbbreviationKorean = deptAbbreviationKorean;
		this.deptNameEnglish = deptNameEnglish;
		this.deptAbbreviationEnglish = deptAbbreviationEnglish;
		this.period = period;
		this.seq = seq;
		this.comment = comment;
		this.parentDept = parentDept;
	}	

	
}
