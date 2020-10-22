package com.like.user.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 로그인, 로그아웃 이력관리 클래스
 * @author cb457
 *
 */
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "COMLOGINHISTORY")
public class LogInOutHistory extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	Long id;
	
	@Column(name="userId")
	String userId;
	
	@Column(name="event_type")
	String eventType;
	
	@Column(name="event_dt")
	LocalDateTime eventDateTime;	
	
	@Column(name="client_ip")
	String clientIp;		
	
	@Column(name="success_yn")
	Boolean isSuccess;	

	/**
	 * @param userId
	 * @param eventType
	 * @param clientIp
	 * @param isSuccess
	 */
	public LogInOutHistory(String userId, String eventType, String clientIp, Boolean isSuccess) {
		this.userId = userId;
		this.eventType = eventType;
		this.eventDateTime = LocalDateTime.now();
		this.clientIp = clientIp;
		this.isSuccess = isSuccess;
	}
}
