package com.like.holiday.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "COMHOLIDAY")
@Entity
public class Holiday extends AuditEntity implements Serializable {	
	
	private static final long serialVersionUID = -1170840034409404138L;

	@Id	
	@Column(name="HOLIDAY_DT")
	private LocalDate date;
		
	@Column(name="HOLIDAY_NM")
	private String holidayName;
	
	@Column(name="CMT")
	private String comment;		
	
}
