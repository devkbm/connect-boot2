package com.like.dept.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 동은정보기술
 *
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true, includeFieldNames = true)
@Builder
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
	
	@Column(name = "from_dt")
	LocalDate fromDate;

	@Column(name = "to_dt")
	LocalDate toDate;
	
	@Builder.Default
	@Column(name="prt_seq")
	int seq = 0;
	
	@Column(name = "cmt")
	String comment;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_dept_cd")
	Dept parentDept;	

}
