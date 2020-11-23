package com.like.hrm.payitem.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.like.common.domain.AuditEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 급여테이블
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMPAYTABLE")
@EntityListeners(AuditingEntityListener.class)
public class PayTable extends AuditEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;	
	
	@Column(name="TABLE_NM")
	String name;
	
	@Column(name="ENABLE_YN")
	Boolean enabled;
	
	@Column(name="TYPE_CODE1")
	String typeCode1;
	
	@Column(name="TYPE_CODE2")
	String typeCode2;
	
	@Column(name="TYPE_CODE3")
	String typeCode3;
	
	@Column(name="CMT")
	String comment;
		
	@OneToMany(mappedBy = "payTable", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	List<PayTableItem> items;
	
	public PayTable(String name
				   ,Boolean enabled
				   ,String typeCode1
				   ,String typeCode2
				   ,String typeCode3
				   ,String comment)	{
		this.name = name;
		this.enabled = enabled;
		this.typeCode1 = typeCode1;
		this.typeCode2 = typeCode2;
		this.typeCode3 = typeCode3;
		this.comment = comment;
	}
	
	public void modify(String name
					  ,Boolean enabled
					  ,String typeCode1
					  ,String typeCode2
					  ,String typeCode3
					  ,String comment) {		
		this.name = name;
		this.enabled = enabled;
		this.typeCode1 = typeCode1;
		this.typeCode2 = typeCode2;
		this.typeCode3 = typeCode3;
		this.comment = comment;
	}
}
