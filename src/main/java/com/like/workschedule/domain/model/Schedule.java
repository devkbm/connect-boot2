package com.like.workschedule.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility=Visibility.ANY)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
	OffsetDateTime start;
	
	@Column(name="END_DT")
	OffsetDateTime end;
	
	@Column(name="ALLDAY")
	Boolean allDay;	
	
	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FK_WORKGROUP", nullable=false, updatable=false)
	WorkGroup workGroup;

	@Builder
	public Schedule(String title, 
					OffsetDateTime start, 
					OffsetDateTime end, 
			        Boolean allDay, 
			        WorkGroup workGroup) {
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
		this.workGroup = workGroup;		
	}

	public void modifyEntity(String title
							,OffsetDateTime start
							,OffsetDateTime end
							,Boolean allDay) {
		this.title = title;
		this.start = start;
		this.end = end;
		this.allDay = allDay;
	}
	
	
}
