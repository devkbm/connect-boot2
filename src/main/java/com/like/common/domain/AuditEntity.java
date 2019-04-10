package com.like.common.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {
	/**
	 * 최초 등록 일시
	 */
	@CreatedDate
	@Column(name = "sys_dt", nullable = false, updatable = false)	
	protected LocalDateTime createdDt;	
	
	/**
	 * 최초 등록 유저	
	 */
	@CreatedBy
	@Column(name = "sys_user", nullable = false, updatable = false)
	protected String createdBy;
	
	/**
	 * 최종 수정 일시		
	 */
	@LastModifiedDate
	@Column(name = "upd_dt")
	protected LocalDateTime modifiedDt;
	
	/**
	 * 최종 수정 유저
	 */
	@LastModifiedBy
	@Column(name = "upd_user")
	protected String modifiedBy;

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	@Override
	public String toString() {
		return "AuditEntity [createdDt=" + createdDt + ", createdBy=" + createdBy + ", modifiedDt=" + modifiedDt
				+ ", modifiedBy=" + modifiedBy + "]";
	}
		
}
