package com.like.hrm.appointment.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.common.domain.AuditEntity;
import com.like.hrm.appointment.domain.model.enums.ChangeType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>발령 코드 기준 정보</p> 
 * [상세] <br/>
 * 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"appointmentCode"})
@Getter
@Entity
@Table(name = "APPOINMENTCODEDETAILS")
@EntityListeners(AuditingEntityListener.class)
public class AppointmentCodeDetails extends AuditEntity implements Serializable {
		
	private static final long serialVersionUID = -9205194638867469788L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PK_CODE_DETAIL")
	private Long pkCodeDetails;	
	
	@Column(name="CHANGE_TYPE")
	private String changeType;
	
	@Column(name="TYPE_CODE")
	private String typeCode;
	
	@Column(name="TYPE_NAME")
	private String typeName;		
				
	@Column(name="CODE_GROUP",insertable=false, updatable=false)
	private String codeGroup;
	
	@Column(name="CODE",insertable=false, updatable=false)
	private String code;
				
	@ManyToOne(fetch=FetchType.LAZY)			
	@JoinColumn(name="code_id")
	private AppointmentCode appointmentCode;	
	
	
}
