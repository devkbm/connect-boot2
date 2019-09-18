package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

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
import com.like.common.domain.AuditEntity;
import com.like.hrm.appointment.domain.model.enums.ChangeType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(callSuper=true, includeFieldNames=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Getter
@Entity
@Table(name = "HRMAPPOINTMENTINFO")
@EntityListeners(AuditingEntityListener.class)
public class LedgerChangeInfo extends AuditEntity implements Serializable {
	
	private static final long serialVersionUID = 6755495351146205498L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_ARTICLE")
	String id;
				
	@Column(name="TYPE_ID")
	String changeType;
	
	@Column(name="CODE")
	String changeCode;
	
	@ManyToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name="LIST_ID", nullable=false, updatable=false)
	LedgerList ledgerList;
}
