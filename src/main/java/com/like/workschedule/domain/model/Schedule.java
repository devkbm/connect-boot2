package com.like.workschedule.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

@Entity
@Table(name = "GRWSCHEDULE")
@EntityListeners(AuditingEntityListener.class)
public class Schedule extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Column(name="TITLE")
	String title;
	
	@Column(name="START_DT")
	LocalDateTime start;
	
	@Column(name="END_DT")
	LocalDateTime end;
	
	@Column(name="ALLDAY")
	Boolean allDay;	
	
	@JoinColumn(name = "FK_WORKGROUP", nullable=false, updatable=false)
	WorkGroup workGroup;
}
