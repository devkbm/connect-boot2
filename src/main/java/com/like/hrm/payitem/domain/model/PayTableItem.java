package com.like.hrm.payitem.domain.model;

import java.math.BigDecimal;
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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "HRMPAYTABLEITEM")
@EntityListeners(AuditingEntityListener.class)
public class PayTableItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Column(name="CODE1")
	String code1;
	
	@Column(name="CODE2")
	String code2;
	
	@Column(name="CODE3")
	String code3;
	
	@Column(name="AMT")
	BigDecimal ammount;
	
	@Column(name="CMT")
	String comment;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAY_TABBLE_ID", nullable=false, updatable=false)
	PayTable payTable;	
	
	public PayTableItem(PayTable payTable
					   ,String code1
					   ,String code2
					   ,String code3
					   ,BigDecimal ammount
					   ,String comment) {
		this.payTable = payTable;
		this.code1 = code1;
		this.code2 = code2;
		this.code3 = code3;
		this.ammount = ammount;
		this.comment = comment;
	}
	
	public void modify(BigDecimal ammount
			          ,String comment) {
		this.ammount = ammount;
		this.comment = comment;
	}
}
